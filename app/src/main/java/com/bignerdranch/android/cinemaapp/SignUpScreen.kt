package com.bignerdranch.android.cinemaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpScreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val sharedPrefFile = "com.example.sharedPrefFile"
    private lateinit var nameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_screen)

        val iHaveAccountButton: Button = findViewById(R.id.iHaveAccountButton)
        iHaveAccountButton.setOnClickListener {
            val intent = Intent(this, SignInScreen::class.java)
            startActivity(intent)
        }

        sharedPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        nameEditText = findViewById(R.id.nameEditText)
        val savedName = sharedPreferences.getString("name", "")
        nameEditText.setText(savedName)

        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            val surnameEditText: EditText = findViewById(R.id.surnameEditText)
            val rePasswordEditText: EditText = findViewById(R.id.re_passwordEditText)
            val emailEditText: EditText = findViewById(R.id.emailEditText)
            val passwordEditText: EditText = findViewById(R.id.passwordEditText)

            if (nameEditText.text.toString().isEmpty() || surnameEditText.text.toString().isEmpty() ||
                emailEditText.text.toString().isEmpty() || passwordEditText.text.toString().isEmpty() ||
                rePasswordEditText.text.toString().isEmpty()) {

                Toast.makeText(this, "Заполните все поля для ввода.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (passwordEditText.text.toString() != rePasswordEditText.text.toString()){
                Toast.makeText(this, "Пароли не совпадают.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!isEmailValid(emailEditText.text.toString())) {
                Toast.makeText(this, "Невверный ввод почты.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()

            editor.putString("email", email)
            editor.putString("password", password)
            editor.putString("name", name)
            editor.putString("surname", surname)
            editor.remove("imagePath")
            editor.apply()

            Toast.makeText(this, "Вы успешно зарегистрировались.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SignInScreen::class.java)
            startActivity(intent)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Regex("[^@]+@[^@]+\\.[^@]+")
        return pattern.matches(email)
    }
}
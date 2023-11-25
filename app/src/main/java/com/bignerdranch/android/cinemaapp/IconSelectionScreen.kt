package com.bignerdranch.android.cinemaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class IconSelectionScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.icon_selection_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backButton = findViewById<ImageView>(R.id.backImage)
        backButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        val ico1 = findViewById<ImageView>(R.id.ico1)
        ico1.setOnClickListener { selectIcon(R.drawable.ico1) }
        val ico2 = findViewById<ImageView>(R.id.ico2)
        ico2.setOnClickListener { selectIcon(R.drawable.ico2) }
        val ico3 = findViewById<ImageView>(R.id.ico3)
        ico3.setOnClickListener { selectIcon(R.drawable.ico3) }
        val ico4 = findViewById<ImageView>(R.id.ico4)
        ico4.setOnClickListener { selectIcon(R.drawable.ico4) }
        val ico5 = findViewById<ImageView>(R.id.ico5)
        ico5.setOnClickListener { selectIcon(R.drawable.ico5) }
        val ico6 = findViewById<ImageView>(R.id.ico6)
        ico6.setOnClickListener { selectIcon(R.drawable.ico6) }
        val ico7 = findViewById<ImageView>(R.id.ico7)
        ico7.setOnClickListener { selectIcon(R.drawable.ico7) }
        val ico8 = findViewById<ImageView>(R.id.ico8)
        ico8.setOnClickListener { selectIcon(R.drawable.ico8) }
        val ico9 = findViewById<ImageView>(R.id.ico9)
        ico9.setOnClickListener { selectIcon(R.drawable.ico9) }
        val ico10 = findViewById<ImageView>(R.id.ico10)
        ico10.setOnClickListener { selectIcon(R.drawable.ico10) }
        val ico11 = findViewById<ImageView>(R.id.ico11)
        ico11.setOnClickListener { selectIcon(R.drawable.ico11) }
        val ico12 = findViewById<ImageView>(R.id.ico12)
        ico12.setOnClickListener { selectIcon(R.drawable.ico12) }
        val ico13 = findViewById<ImageView>(R.id.ico13)
        ico13.setOnClickListener { selectIcon(R.drawable.ico13) }
        val ico14 = findViewById<ImageView>(R.id.ico14)
        ico14.setOnClickListener { selectIcon(R.drawable.ico14) }
        val ico15 = findViewById<ImageView>(R.id.ico15)
        ico15.setOnClickListener { selectIcon(R.drawable.ico15) }
        val ico16 = findViewById<ImageView>(R.id.ico16)
        ico16.setOnClickListener { selectIcon(R.drawable.ico16) }
        val ico17 = findViewById<ImageView>(R.id.ico17)
        ico17.setOnClickListener { selectIcon(R.drawable.ico17) }
        val ico18 = findViewById<ImageView>(R.id.ico18)
        ico18.setOnClickListener { selectIcon(R.drawable.ico18) }
        val ico19 = findViewById<ImageView>(R.id.ico19)
        ico19.setOnClickListener { selectIcon(R.drawable.ico19) }
        val ico20 = findViewById<ImageView>(R.id.ico20)
        ico20.setOnClickListener { selectIcon(R.drawable.ico20) }
        val ico21 = findViewById<ImageView>(R.id.ico21)
        ico21.setOnClickListener { selectIcon(R.drawable.ico21) }
        val ico22 = findViewById<ImageView>(R.id.ico22)
        ico22.setOnClickListener { selectIcon(R.drawable.ico22) }
        val ico23 = findViewById<ImageView>(R.id.ico23)
        ico23.setOnClickListener { selectIcon(R.drawable.ico23) }
        val ico24 = findViewById<ImageView>(R.id.ico24)
        ico24.setOnClickListener { selectIcon(R.drawable.ico24) }
        val ico25 = findViewById<ImageView>(R.id.ico25)
        ico25.setOnClickListener { selectIcon(R.drawable.ico25) }
        val ico26 = findViewById<ImageView>(R.id.ico26)
        ico26.setOnClickListener { selectIcon(R.drawable.ico26) }
        val ico27 = findViewById<ImageView>(R.id.ico27)
        ico27.setOnClickListener { selectIcon(R.drawable.ico27) }
        val ico28 = findViewById<ImageView>(R.id.ico28)
        ico28.setOnClickListener { selectIcon(R.drawable.ico28) }
        val ico29 = findViewById<ImageView>(R.id.ico29)
        ico29.setOnClickListener { selectIcon(R.drawable.ico29) }
        val ico30 = findViewById<ImageView>(R.id.ico30)
        ico30.setOnClickListener { selectIcon(R.drawable.ico30) }
        val ico31 = findViewById<ImageView>(R.id.ico31)
        ico31.setOnClickListener { selectIcon(R.drawable.ico31) }
        val ico32 = findViewById<ImageView>(R.id.ico32)
        ico32.setOnClickListener { selectIcon(R.drawable.ico32) }
        val ico33 = findViewById<ImageView>(R.id.ico33)
        ico33.setOnClickListener { selectIcon(R.drawable.ico33) }
        val ico34 = findViewById<ImageView>(R.id.ico34)
        ico34.setOnClickListener { selectIcon(R.drawable.ico34) }
        val ico35 = findViewById<ImageView>(R.id.ico35)
        ico35.setOnClickListener { selectIcon(R.drawable.ico35) }
        val ico36 = findViewById<ImageView>(R.id.ico36)
        ico36.setOnClickListener { selectIcon(R.drawable.ico36) }

    }

    private fun selectIcon(iconId: Int) {
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_SELECTED_ICON, iconId)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val EXTRA_SELECTED_ICON = "selected_icon"
    }
}
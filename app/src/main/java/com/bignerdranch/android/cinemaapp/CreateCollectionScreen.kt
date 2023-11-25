package com.bignerdranch.android.cinemaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

// окно для создания новой коллекции
class CreateCollectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_collection_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backButton = findViewById<ImageView>(R.id.backImage)
        backButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finishWithAnimation()
        }

        val selectIconButton = findViewById<Button>(R.id.selectIconButton)
        selectIconButton.setOnClickListener {
            val intent = Intent(this, IconSelectionScreen::class.java)
            startActivityForResult(intent, REQUEST_SELECT_ICON)
        }

        val createButton = findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener {
            val collectionName = findViewById<EditText>(R.id.nameEditText).text.toString()
            val selectedIcon = findViewById<ImageView>(R.id.selectedIconImageView).tag as? Int
            if (collectionName.isNotEmpty()) {
                if (selectedIcon != null) {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_COLLECTION_NAME, collectionName)
                    resultIntent.putExtra(EXTRA_SELECTED_ICON, selectedIcon)
                    setResult(RESULT_OK, resultIntent)
                }
                else {
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_COLLECTION_NAME, collectionName)
                    resultIntent.putExtra(EXTRA_SELECTED_ICON, R.drawable.ico1)
                    setResult(RESULT_OK, resultIntent)
                }
            } else {
                setResult(RESULT_CANCELED)
            }
            finish()
        }
    }

    private fun finishWithAnimation() {
        finish()
        overridePendingTransition(R.anim.slide_out_right, R.anim.fade_out)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_ICON && resultCode == RESULT_OK) {
            val selectedIcon = data?.getIntExtra(IconSelectionScreen.EXTRA_SELECTED_ICON, R.drawable.ico1)
            if (selectedIcon != null) {
                val selectedIconImageView = findViewById<ImageView>(R.id.selectedIconImageView)
                selectedIconImageView.setImageResource(selectedIcon)
                selectedIconImageView.tag = selectedIcon
            }
        }
    }

    companion object {
        const val EXTRA_COLLECTION_NAME = "collection_name"
        const val EXTRA_SELECTED_ICON = "selected_icon"
        private const val REQUEST_SELECT_ICON = 1
    }
}
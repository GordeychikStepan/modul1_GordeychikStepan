package com.bignerdranch.android.cinemaapp

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ChatScreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val sharedPrefFile = "com.example.sharedPrefFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)

        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val surname = sharedPreferences.getString("surname", "")

        val chatNumber = intent.getIntExtra("chatNumber", -1)

        if (chatNumber == -1) {
            finish()
            return
        }

        val chatList = mutableListOf<ChatModel>()
        val chatModel = MessagesData.getChatByNumber(chatNumber, name, surname, this)
        chatList.add(chatModel)

        val chatRecyclerView: RecyclerView = findViewById(R.id.chatRecyclerView)
        val messageEditText: EditText = findViewById(R.id.messageEditText)
        val sendButton: Button = findViewById(R.id.sendButton)
        val chatTitleTextView: TextView = findViewById(R.id.chatTitleTextView)
        val backButton: ImageView = findViewById(R.id.backImage)

        chatTitleTextView.text = chatModel.chatName

        val layoutManager = LinearLayoutManager(this)
        chatRecyclerView.layoutManager = layoutManager
        val chatAdapter = ChatAdapter(chatList)
        chatRecyclerView.adapter = chatAdapter

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString()
            if (messageText.isNotEmpty()) {

                val currentDate = getCurrentTimeString()

                val imagePath = sharedPreferences.getString("imagePath", null)
                Log.d("ImagePath", "Path: $imagePath")

                val newMessage = MessageModel(messageText, currentDate, true, "$name $surname", null)

                if (!imagePath.isNullOrEmpty()) {
                    newMessage.profilePhoto = Uri.parse(imagePath)
                } else {
                    val packageName = "com.bignerdranch.android.cinemaapp"
                    newMessage.profilePhoto = Uri.parse("android.resource://$packageName/${R.drawable.user_profile}")
                }

                chatModel.messages.add(newMessage)
                chatAdapter.notifyItemChanged(0)
                messageEditText.text.clear()

                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(messageEditText.windowToken, 0)

                chatRecyclerView.smoothScrollToPosition(chatModel.messages.size - 1)
            }
        }


        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_out_right, R.anim.fade_out)
        }
    }

    private fun getCurrentTimeString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }
}

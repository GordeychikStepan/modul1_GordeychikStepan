package com.bignerdranch.android.cinemaapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatListScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list_screen)

        val chatListRecyclerView: RecyclerView = findViewById(R.id.chatListRecyclerView)
        val backButton: ImageView = findViewById(R.id.backImage)

        val layoutManager = LinearLayoutManager(this)
        chatListRecyclerView.layoutManager = layoutManager

        val chatList = MessagesData.getAllChats("", "", this)

        val chatListAdapter = ChatListAdapter(chatList)
        chatListRecyclerView.adapter = chatListAdapter

        chatListAdapter.setOnItemClickListener { chatModel ->
            val intent = Intent(this, ChatScreen::class.java)
            intent.putExtra("chatNumber", chatModel.chatNumber)
            val options = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }

        backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_out_right, R.anim.fade_out)
        }
    }
}

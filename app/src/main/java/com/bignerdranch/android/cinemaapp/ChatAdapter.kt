package com.bignerdranch.android.cinemaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter (private val chatList: List<ChatModel>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.bind(chat)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messagesRecyclerView: RecyclerView = itemView.findViewById(R.id.messagesRecyclerView)

        fun bind(chat: ChatModel) {
            val layoutManager = LinearLayoutManager(itemView.context)
            messagesRecyclerView.layoutManager = layoutManager
            messagesRecyclerView.adapter = MessageAdapter(chat.messages)
        }
    }
}
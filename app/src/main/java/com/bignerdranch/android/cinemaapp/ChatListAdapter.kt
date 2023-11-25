package com.bignerdranch.android.cinemaapp

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatListAdapter(private val chatList: List<ChatModel>) :
    RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>() {

    private var itemClickListener: ((ChatModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatModel = chatList[position]
        holder.bind(chatModel)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chatImageView: ImageView = itemView.findViewById(R.id.chatImageView)
        private val chatNameTextView: TextView = itemView.findViewById(R.id.chatNameTextView)
        private val lastMessageTextView: TextView = itemView.findViewById(R.id.lastMessageTextView)

        init {
            itemView.setOnClickListener {
                itemClickListener?.invoke(chatList[adapterPosition])
            }
        }

        fun bind(chatModel: ChatModel) {
            chatImageView.setImageResource(chatModel.imageResId)
            chatNameTextView.text = chatModel.chatName

            // создаем SpannableString
            val lastMessage = chatModel.getLastMessageTextWithSender()
            val spannableString = SpannableString(lastMessage)

            // определяем позицию "имя:" в строке
            val colonIndex = lastMessage.indexOf(':')

            // устанавливаем серый цвет для имени
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#AFAFAF")),
                0,
                colonIndex + 1, // +1, чтобы включить ":" в область
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // устанавливаем белый цвет для остальной части сообщения
            spannableString.setSpan(
                ForegroundColorSpan(Color.WHITE),
                colonIndex + 1,
                lastMessage.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            lastMessageTextView.text = spannableString
        }
    }

    fun setOnItemClickListener(listener: (ChatModel) -> Unit) {
        itemClickListener = listener
    }
}

package com.bignerdranch.android.cinemaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(
    private val messageList: List<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DATE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false)
                DateViewHolder(view)
            }
            else -> {
                val layoutRes = when (viewType) {
                    NORMAL_RECEIVED -> R.layout.item_message_received
                    LAST_RECEIVED -> R.layout.item_message_received_last
                    NORMAL_SENT -> R.layout.item_message_sent
                    LAST_SENT -> R.layout.item_message_sent_last
                    else -> throw IllegalArgumentException("Invalid view type")
                }
                val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
                MessageViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder -> {
                val message = messageList[position]
                holder.bindMessage(message)

                val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                params.setMargins(0, if (position > 0) getMessageMargin(holder, position) else 0, 0, 0)
                holder.itemView.layoutParams = params
            }
            is DateViewHolder -> {
                val dateMessage = messageList[position]
                holder.bind(dateMessage.date)
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].isDateMessage) {
            VIEW_TYPE_DATE
        } else {
            val current = messageList[position]
            val previous = if (position > 0) messageList[position - 1] else null

            when {
                current.isSentByMe -> {
                    if (previous == null || current.userName != previous.userName) {
                        NORMAL_SENT
                    } else {
                        LAST_SENT
                    }
                }
                else -> {
                    if (previous == null || current.userName != previous.userName) {
                        NORMAL_RECEIVED
                    } else {
                        LAST_RECEIVED
                    }
                }
            }
        }
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        private val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)

        fun bindMessage(message: MessageModel) {
            messageTextView.text = message.text
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = dateFormat.parse(message.date)
            val formattedTimestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
            timestampTextView.text = formattedTimestamp
            userNameTextView.text = message.userName

            val isLastFromUser = layoutPosition == messageList.lastIndex || message.userName != messageList[layoutPosition + 1].userName
            if (isLastFromUser) {
                if (message.profilePhoto != null) {
                    profileImageView.setImageURI(message.profilePhoto)
                } else {
                    profileImageView.setImageResource(R.drawable.user_profile)
                }
            } else {
                profileImageView.setImageResource(R.color.background)
            }
        }
    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(date: String) {
            dateTextView.text = date
        }
    }

    private fun getMessageMargin(holder: RecyclerView.ViewHolder, position: Int): Int {
        val current = messageList[position]
        val previous = if (position > 0) messageList[position - 1] else null

        return if (previous != null && current.userName == previous.userName) {
            holder.itemView.context.resources.getDimensionPixelSize(R.dimen.message_margin_base)
        } else {
            holder.itemView.context.resources.getDimensionPixelSize(R.dimen.message_margin)
        }
    }

    companion object {
        const val NORMAL_RECEIVED = 1
        const val LAST_RECEIVED = 2
        const val NORMAL_SENT = 3
        const val LAST_SENT = 4
        const val VIEW_TYPE_DATE = 5
    }
}

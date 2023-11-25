package com.bignerdranch.android.cinemaapp

data class ChatModel(
    val chatNumber: Int,
    val chatName: String,
    val imageResId: Int,
    val messages: MutableList<MessageModel>
) {
    fun getLastMessageTextWithSender(): String {
        val lastMessage = messages.lastOrNull()
        return if (lastMessage != null) {
            val firstName = extractFirstName(lastMessage.userName)
            "$firstName: ${lastMessage.text}"
        } else {
            ""
        }
    }

    private fun extractFirstName(fullName: String): String {
        return fullName.split(" ")[0]
    }
}


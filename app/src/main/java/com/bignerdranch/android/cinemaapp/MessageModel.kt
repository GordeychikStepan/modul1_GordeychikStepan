package com.bignerdranch.android.cinemaapp

import android.net.Uri

data class MessageModel(
    val text: String,
    val date: String,
    val isSentByMe: Boolean,
    val userName: String,
    var profilePhoto: Uri? = null,
    val isDateMessage: Boolean = false
)

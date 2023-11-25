package com.bignerdranch.android.cinemaapp

import android.content.Context
import android.net.Uri
import android.util.Log

object MessagesData {

    fun getChatByNumber(
        chatNumber: Int,
        name: String?,
        surname: String?,
        chatScreen: ChatScreen
    ): ChatModel {
        return when (chatNumber) {
            1 -> createChat1(name, surname, chatScreen)
            2 -> createChat2(name, surname, chatScreen)
            3 -> createChat3(name, surname, chatScreen)
            4 -> createChat4(name, surname, chatScreen)
            else -> createDefaultChat()
        }
    }

    fun getAllChats(s: String, s1: String, chatListScreen: ChatListScreen): List<ChatModel> {
        return listOf(
            createChat1(s, s1, chatListScreen),
            createChat2(s, s1, chatListScreen),
            createChat3(s, s1, chatListScreen),
            createChat4(s, s1, chatListScreen),
        )
    }

    private fun createChat1(name: String?, surname: String?, context: Context): ChatModel {
        val packageName = "com.bignerdranch.android.cinemaapp"
        val sharedPrefFile = "com.example.sharedPrefFile"

        val sharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val imagePath = sharedPreferences.getString("imagePath", null)
        Log.d("ImagePath", "Path: $imagePath")

        val image: Uri = if (!imagePath.isNullOrEmpty()) {
            Uri.parse(imagePath)
        } else {
            Uri.parse("android.resource://$packageName/${R.drawable.user_profile}")
        }

        return ChatModel(
            chatNumber = 1,
            chatName = "Игра престолов",
            imageResId = R.drawable.ico_chat1_2,
            messages = mutableListOf(
                MessageModel("", "19 апреля", false, "", null, true),
                MessageModel("Завтра уже выйдет финальная серия!!!", "2023-11-22 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Скорее бы!", "2023-11-22 18:30:30", true, "$name $surname", image),
                MessageModel("", "Сегодня", false, "", null, true),
                MessageModel("Как вам последняя серия?", "2023-11-23 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Мне кажется, достойное завершение. Кто бы что ни говорил, а мне понравилось.", "2023-11-23 18:30:30", true, "$name $surname", image),
                MessageModel("Тоже так считаю!", "2023-11-23 18:40:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
                MessageModel("Пересматривала несколько раз, очень круто снято.", "2023-11-23 18:41:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
            )
        )
    }


    private fun createChat2(name: String?, surname: String?, context: Context): ChatModel {
        val packageName = "com.bignerdranch.android.cinemaapp"
        val sharedPrefFile = "com.example.sharedPrefFile"

        val sharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val imagePath = sharedPreferences.getString("imagePath", null)
        Log.d("ImagePath", "Path: $imagePath")

        val image: Uri = if (!imagePath.isNullOrEmpty()) {
            Uri.parse(imagePath)
        } else {
            Uri.parse("android.resource://$packageName/${R.drawable.user_profile}")
        }

        return ChatModel(
            chatNumber = 2,
            chatName = "Игра престолов",
            imageResId = R.drawable.ico_chat2,
            messages = mutableListOf(
                MessageModel("", "19 апреля", false, "", null, true),
                MessageModel("Завтра уже выйдет финальная серия!!!", "2023-11-22 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Скорее бы!", "2023-11-22 18:30:30", true, "$name $surname", image),
                MessageModel("", "Сегодня", false, "", null, true),
                MessageModel("Как вам последняя серия?", "2023-11-23 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Мне кажется, достойное завершение. Кто бы что ни говорил, а мне понравилось.", "2023-11-23 18:30:30", true, "$name $surname", image),
                MessageModel("Тоже так считаю!", "2023-11-23 18:40:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
                MessageModel("Пересматривала несколько раз, очень круто снято.", "2023-11-23 18:41:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
            )
        )
    }

    private fun createChat3(name: String?, surname: String?, context: Context): ChatModel {
        val packageName = "com.bignerdranch.android.cinemaapp"
        val sharedPrefFile = "com.example.sharedPrefFile"

        val sharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val imagePath = sharedPreferences.getString("imagePath", null)
        Log.d("ImagePath", "Path: $imagePath")

        val image: Uri = if (!imagePath.isNullOrEmpty()) {
            Uri.parse(imagePath)
        } else {
            Uri.parse("android.resource://$packageName/${R.drawable.user_profile}")
        }

        return ChatModel(
            chatNumber = 3,
            chatName = "Игра престолов",
            imageResId = R.drawable.ico_chat3,
            messages = mutableListOf(
                MessageModel("", "19 апреля", false, "", null, true),
                MessageModel("Завтра уже выйдет финальная серия!!!", "2023-11-22 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Скорее бы!", "2023-11-22 18:30:30", true, "$name $surname", image),
                MessageModel("", "Сегодня", false, "", null, true),
                MessageModel("Как вам последняя серия?", "2023-11-23 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Мне кажется, достойное завершение. Кто бы что ни говорил, а мне понравилось.", "2023-11-23 18:30:30", true, "$name $surname", image),
                MessageModel("Тоже так считаю!", "2023-11-23 18:40:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
                MessageModel("Пересматривала несколько раз, очень круто снято.", "2023-11-23 18:41:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
            )
        )
    }

    private fun createChat4(name: String?, surname: String?, context: Context): ChatModel {
        val packageName = "com.bignerdranch.android.cinemaapp"
        val sharedPrefFile = "com.example.sharedPrefFile"

        val sharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        val imagePath = sharedPreferences.getString("imagePath", null)
        Log.d("ImagePath", "Path: $imagePath")

        val image: Uri = if (!imagePath.isNullOrEmpty()) {
            Uri.parse(imagePath)
        } else {
            Uri.parse("android.resource://$packageName/${R.drawable.user_profile}")
        }

        return ChatModel(
            chatNumber = 4,
            chatName = "Игра престолов",
            imageResId = R.drawable.ico_chat1_2,
            messages = mutableListOf(
                MessageModel("", "19 апреля", false, "", null, true),
                MessageModel("Завтра уже выйдет финальная серия!!!", "2023-11-22 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Скорее бы!", "2023-11-22 18:30:30", true, "$name $surname", image),
                MessageModel("", "Сегодня", false, "", null, true),
                MessageModel("Как вам последняя серия?", "2023-11-23 18:21:00", false, "Агата петровна", Uri.parse("android.resource://$packageName/${R.drawable.agata_petrovna}")),
                MessageModel("Мне кажется, достойное завершение. Кто бы что ни говорил, а мне понравилось.", "2023-11-23 18:30:30", true, "$name $surname", image),
                MessageModel("Тоже так считаю!", "2023-11-23 18:40:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
                MessageModel("Пересматривала несколько раз, очень круто снято.", "2023-11-23 18:41:00", false, "Макс Потапов", Uri.parse("android.resource://$packageName/${R.drawable.maxim_potapov}")),
            )
        )
    }

    private fun createDefaultChat(): ChatModel {
        val packageName = "com.bignerdranch.android.cinemaapp"
        return ChatModel(
            chatNumber = 0,
            chatName = "Стандартный чат",
            imageResId = R.drawable.ico_chat1_2,
            messages = mutableListOf(
                MessageModel("Привет! Это стандартный чат.", "2023-11-20 15:15:30", false, "Админ", Uri.parse("android.resource://$packageName/${R.drawable.user_profile}"))
            )
        )
    }
}
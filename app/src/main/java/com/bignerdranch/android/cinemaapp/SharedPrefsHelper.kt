package com.bignerdranch.android.cinemaapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // сохранение коллекции в SharedPreferences
    fun saveCollections(collections: List<Collection>) {
        val json = gson.toJson(collections)
        sharedPreferences.edit().putString("collections", json).apply()
    }

    // загрузка сохраненных коллекций из SharedPreferences
    fun loadCollections(): List<Collection> {
        val json = sharedPreferences.getString("collections", null)
        return if (json != null) {
            val type = object : TypeToken<List<Collection>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}
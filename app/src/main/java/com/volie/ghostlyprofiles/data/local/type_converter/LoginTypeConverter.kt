package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Login

class LoginTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLogin(login: Login): String {
        return gson.toJson(login)
    }

    @TypeConverter
    fun toLogin(json: String): Login {
        return gson.fromJson(json, Login::class.java)
    }
}
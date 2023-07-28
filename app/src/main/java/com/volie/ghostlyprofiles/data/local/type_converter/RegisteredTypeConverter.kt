package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Registered

class RegisteredTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRegistered(registered: Registered): String {
        return gson.toJson(registered)
    }

    @TypeConverter
    fun toRegistered(json: String): Registered {
        return gson.fromJson(json, Registered::class.java)
    }
}
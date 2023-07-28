package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Name

class NameTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromName(name: Name): String {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toName(json: String): Name {
        return gson.fromJson(json, Name::class.java)
    }
}
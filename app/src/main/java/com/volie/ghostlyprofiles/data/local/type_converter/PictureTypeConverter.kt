package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Picture

class PictureTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPicture(picture: Picture): String {
        return gson.toJson(picture)
    }

    @TypeConverter
    fun toPicture(json: String): Picture {
        return gson.fromJson(json, Picture::class.java)
    }
}
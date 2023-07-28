package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Dob

class DobTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDob(dob: Dob): String {
        return gson.toJson(dob)
    }

    @TypeConverter
    fun toDob(json: String): Dob {
        return gson.fromJson(json, Dob::class.java)
    }
}
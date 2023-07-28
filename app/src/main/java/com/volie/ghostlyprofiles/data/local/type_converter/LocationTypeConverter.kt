package com.volie.ghostlyprofiles.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.volie.ghostlyprofiles.data.model.Location

class LocationTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(json: String): Location {
        return gson.fromJson(json, Location::class.java)
    }
}
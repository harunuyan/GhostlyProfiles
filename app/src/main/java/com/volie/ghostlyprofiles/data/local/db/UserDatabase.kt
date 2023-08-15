package com.volie.ghostlyprofiles.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.volie.ghostlyprofiles.data.local.type_converter.DobTypeConverter
import com.volie.ghostlyprofiles.data.local.type_converter.LocationTypeConverter
import com.volie.ghostlyprofiles.data.local.type_converter.LoginTypeConverter
import com.volie.ghostlyprofiles.data.local.type_converter.NameTypeConverter
import com.volie.ghostlyprofiles.data.local.type_converter.PictureTypeConverter
import com.volie.ghostlyprofiles.data.local.type_converter.RegisteredTypeConverter
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.data.model.User

@Database(entities = [User::class, Login::class], version = 1, exportSchema = false)
@TypeConverters(
    DobTypeConverter::class,
    LocationTypeConverter::class,
    LoginTypeConverter::class,
    NameTypeConverter::class,
    PictureTypeConverter::class,
    RegisteredTypeConverter::class
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
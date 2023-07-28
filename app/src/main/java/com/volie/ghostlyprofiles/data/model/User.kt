package com.volie.ghostlyprofiles.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val location: Location,
    val login: Login,
    var isLiked: Boolean = false,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
) : Parcelable
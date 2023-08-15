package com.volie.ghostlyprofiles.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "password_table")
@Parcelize
data class Login(
    val name: String = "",
    val md5: String = "",
    @PrimaryKey()
    val password: String = "",
    val sha1: String = "",
    val sha256: String = "",
    val username: String = "",
    val uuid: String = ""
) : Parcelable
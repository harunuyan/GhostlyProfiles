package com.volie.ghostlyprofiles.data.model

data class Login(
    val md5: String,
    val password: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
)
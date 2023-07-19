package com.volie.ghostlyprofiles.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("")
    suspend fun getRandomUsersByFilter(
        @Query("nat")
        nat: String? = ""
    )
}
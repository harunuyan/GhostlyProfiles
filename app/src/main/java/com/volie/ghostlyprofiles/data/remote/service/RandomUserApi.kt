package com.volie.ghostlyprofiles.data.remote.service

import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results")
        results: Int? = 80,
        @Query("nat")
        nat: String = "",
        @Query("gender")
        gender: String? = ""
    ): Response<RandomUserResponse>

    @GET("api/")
    suspend fun getAccordingToIncludeField(
        @Query("inc")
        fields: String
    ): Response<RandomUserResponse>
}
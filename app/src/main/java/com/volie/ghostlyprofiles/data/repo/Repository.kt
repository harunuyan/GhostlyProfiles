package com.volie.ghostlyprofiles.data.repo

import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import com.volie.ghostlyprofiles.data.remote.service.RandomUserApi
import com.volie.ghostlyprofiles.util.Resource
import javax.inject.Inject

class Repository
@Inject constructor(
    private val service: RandomUserApi,
) {
    suspend fun getRandomUsers(): Resource<RandomUserResponse> {
        return try {
            val response = service.getRandomUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }
}
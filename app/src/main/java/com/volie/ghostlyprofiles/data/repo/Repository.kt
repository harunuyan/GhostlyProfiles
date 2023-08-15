package com.volie.ghostlyprofiles.data.repo

import com.volie.ghostlyprofiles.data.local.db.UserDao
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.data.model.RandomUserResponse
import com.volie.ghostlyprofiles.data.model.User
import com.volie.ghostlyprofiles.data.remote.service.RandomUserApi
import com.volie.ghostlyprofiles.util.Resource
import javax.inject.Inject

class Repository
@Inject constructor(
    private val service: RandomUserApi,
    private val dao: UserDao
) {
    suspend fun getRandomUsersFromRemote(
        nat: String,
        gender: String
    ): Resource<RandomUserResponse> {
        return try {
            val response = service.getRandomUsers(nat = nat, gender = gender)
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

    suspend fun generatePassword(passwordOptions: String): Resource<RandomUserResponse> {
        return try {
            val response = service.generatePassword(passwordOptions)
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

    suspend fun getSavedUsersFromDatabase(): List<User> {
        return dao.getSavedUsers()
    }

    suspend fun getSavedPasswordsFromDatabase(): List<Login> {
        return dao.getSavedPasswords()
    }

    suspend fun insertUser(user: User) = dao.insertUser(user)

    suspend fun insertPassword(password: Login) = dao.insertPassword(password)

    suspend fun deleteUser(user: User) = dao.deleteUser(user)

    suspend fun deletePassword(password: Login) = dao.deletePassword(password)

    suspend fun deleteAllUsers() = dao.deleteAllUsers()
}
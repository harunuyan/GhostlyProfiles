package com.volie.ghostlyprofiles.data.repo

import com.volie.ghostlyprofiles.data.local.db.UserDao
import com.volie.ghostlyprofiles.data.model.Country
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

    fun getCountries(): Array<Country> {
        return arrayOf(
            Country(name = "Australia", countryCode = "AU"),
            Country(name = "Brazil", countryCode = "BR"),
            Country(name = "Canada", countryCode = "CA"),
            Country(name = "Switzerland", countryCode = "CH"),
            Country(name = "Germany", countryCode = "DE"),
            Country(name = "Denmark", countryCode = "DK"),
            Country(name = "Spain", countryCode = "ES"),
            Country(name = "Finland", countryCode = "FI"),
            Country(name = "France", countryCode = "FR"),
            Country(name = "United Kingdom", countryCode = "GB"),
            Country(name = "Ireland", countryCode = "IE"),
            Country(name = "India", countryCode = "IN"),
            Country(name = "Iran", countryCode = "IR"),
            Country(name = "Mexico", countryCode = "MX"),
            Country(name = "Netherlands", countryCode = "NL"),
            Country(name = "Norway", countryCode = "NO"),
            Country(name = "New Zealand", countryCode = "NZ"),
            Country(name = "Serbia", countryCode = "RS"),
            Country(name = "Turkey", countryCode = "TR"),
            Country(name = "Ukraine", countryCode = "UA")
        )
    }
}
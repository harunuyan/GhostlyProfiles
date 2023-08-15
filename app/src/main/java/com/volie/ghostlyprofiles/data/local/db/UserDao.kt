package com.volie.ghostlyprofiles.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.volie.ghostlyprofiles.data.model.Login
import com.volie.ghostlyprofiles.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getSavedUsers(): List<User>

    @Query("SELECT * FROM password_table")
    suspend fun getSavedPasswords(): List<Login>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(password: Login)

    @Delete
    suspend fun deleteUser(user: User)

    @Delete
    suspend fun deletePassword(password: Login)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}
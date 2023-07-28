package com.volie.ghostlyprofiles.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.volie.ghostlyprofiles.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getSavedUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
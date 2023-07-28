package com.volie.ghostlyprofiles.data.di

import android.content.Context
import androidx.room.Room
import com.volie.ghostlyprofiles.data.local.db.UserDao
import com.volie.ghostlyprofiles.data.local.db.UserDatabase
import com.volie.ghostlyprofiles.util.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesUserDao(database: UserDatabase): UserDao {
        return database.getUserDao()
    }
}
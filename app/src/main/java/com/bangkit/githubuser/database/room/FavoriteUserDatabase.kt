package com.bangkit.githubuser.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1)
abstract class FavoriteUserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteUserDatabase? = null

        fun getInstance(context: Context): FavoriteUserDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUserDatabase::class.java,
                    "FavoriteUser.db"
                ).build()
            }
    }
}
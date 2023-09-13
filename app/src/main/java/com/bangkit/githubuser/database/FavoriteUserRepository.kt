package com.bangkit.githubuser.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bangkit.githubuser.data.retrofit.ApiService
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity
import com.bangkit.githubuser.database.room.UserDao
import com.bangkit.githubuser.util.AppExecutors

class FavoriteUserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val appExecutors: AppExecutors
) {
    private val result =
        MediatorLiveData<com.bangkit.githubuser.database.Result<List<FavoriteUserEntity>>>()

    suspend fun setBookmarkedNews(user: FavoriteUserEntity) {
        return userDao.insert(user)
    }

    fun getFavoriteByUser(user: String): LiveData<FavoriteUserEntity> {
        return userDao.getFavoriteByUser(user)
    }

    suspend fun deleteUser(user: String) {
        userDao.delete(user)
    }

    fun getAllFavoriteUser(): LiveData<List<FavoriteUserEntity>> {

        return userDao.getAllFavoriteUser()
    }

    companion object {
        @Volatile
        private var instance: FavoriteUserRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: UserDao,
            appExecutors: AppExecutors
        ): FavoriteUserRepository {
            return instance ?: synchronized(this) {
                instance ?: FavoriteUserRepository(apiService, newsDao, appExecutors)
            }.also {
                instance = it
            }
        }
    }
}
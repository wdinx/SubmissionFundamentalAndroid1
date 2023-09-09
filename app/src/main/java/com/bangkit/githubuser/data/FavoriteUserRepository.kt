package com.bangkit.githubuser.data

import androidx.lifecycle.MediatorLiveData
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.retrofit.ApiService
import com.bangkit.githubuser.database.FavoriteUser
import com.bangkit.githubuser.database.UserDao
import com.bangkit.githubuser.util.AppExecutors

class FavoriteUserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val appExecutors: AppExecutors,
) {
    private val result = MediatorLiveData<Result<List<FavoriteUser>>>()

    suspend fun setBookmarkedUser(favoriteUser: FavoriteUser) {
        userDao.insert(favoriteUser)
    }

    suspend fun checkUserId(id: Int) = userDao.checkUserId(id)

    companion object {
        @Volatile
        private var instance: FavoriteUserRepository? = null

        fun getInstance(
            apiService: ApiService,
            userDao: UserDao,
            appExecutors: AppExecutors,
            userResponse: UserResponse
        ): FavoriteUserRepository {
            return instance ?: synchronized(this) {
                instance ?: FavoriteUserRepository(apiService, userDao, appExecutors)
            }.also {
                instance = it
            }
        }
    }
}
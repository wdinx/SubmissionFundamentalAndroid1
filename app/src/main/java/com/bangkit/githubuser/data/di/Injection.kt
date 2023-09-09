package com.bangkit.githubuser.data.di

import android.content.Context
import com.bangkit.githubuser.data.FavoriteUserRepository
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.retrofit.ApiConfig
import com.bangkit.githubuser.database.FavoriteUser
import com.bangkit.githubuser.database.UserRoomDatabase
import com.bangkit.githubuser.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): FavoriteUserRepository{
        val apiService = ApiConfig.getApiService()
        val database = UserRoomDatabase.getInstance(context)
        val dao = database.userDao()
        val appExecutors = AppExecutors()
        val userResponse = UserResponse()

        return FavoriteUserRepository.getInstance(apiService, dao, appExecutors, userResponse)
    }
}
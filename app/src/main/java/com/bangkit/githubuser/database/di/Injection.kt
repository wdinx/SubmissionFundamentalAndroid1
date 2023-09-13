package com.bangkit.githubuser.database.di

import android.content.Context
import com.bangkit.githubuser.data.retrofit.ApiConfig
import com.bangkit.githubuser.database.FavoriteUserRepository
import com.bangkit.githubuser.database.room.FavoriteUserDatabase
import com.bangkit.githubuser.util.AppExecutors

object Injection {
    fun providerRepostiory(context: Context): FavoriteUserRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteUserDatabase.getInstance(context)
        val dao = database.userDao()
        val appExecutors = AppExecutors()

        return FavoriteUserRepository.getInstance(apiService, dao, appExecutors)
    }
}
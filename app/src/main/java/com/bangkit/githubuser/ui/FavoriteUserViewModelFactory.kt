package com.bangkit.githubuser.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.githubuser.database.FavoriteUserRepository
import com.bangkit.githubuser.database.di.Injection

class FavoriteUserViewModelFactory private constructor(private val favoriteUserRepository: FavoriteUserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUserViewModel::class.java)) {
            return FavoriteUserViewModel(favoriteUserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: FavoriteUserViewModelFactory? = null

        fun getInstance(context: Context): FavoriteUserViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: FavoriteUserViewModelFactory(Injection.providerRepostiory(context))
            }.also { instance = it }
    }
}
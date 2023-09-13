package com.bangkit.githubuser.ui

import androidx.lifecycle.ViewModel
import com.bangkit.githubuser.database.FavoriteUserRepository

class FavoriteUserViewModel(private val favoriteUserRepository: FavoriteUserRepository) :
    ViewModel() {
    fun getAllFavoriteByUser() = favoriteUserRepository.getAllFavoriteUser()
}
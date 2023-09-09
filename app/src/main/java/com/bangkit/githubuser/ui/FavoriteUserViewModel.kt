package com.bangkit.githubuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.githubuser.data.FavoriteUserRepository
import com.bangkit.githubuser.database.FavoriteUser
import kotlinx.coroutines.launch

class FavoriteUserViewModel (private val favoriteUserRepository: FavoriteUserRepository): ViewModel() {

    fun saveFavoriteUser(user: FavoriteUser){
        viewModelScope.launch {
            favoriteUserRepository.setBookmarkedUser(user)
        }
    }

    fun checkUserById(id: Int) {
        viewModelScope.launch {
            favoriteUserRepository.checkUserId(id)
        }
    }
}
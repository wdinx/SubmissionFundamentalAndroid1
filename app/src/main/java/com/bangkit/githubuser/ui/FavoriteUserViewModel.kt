package com.bangkit.githubuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.githubuser.data.retrofit.ApiService
import com.bangkit.githubuser.database.FavoriteUserRepository
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity
import com.bangkit.githubuser.database.room.UserDao
import com.bangkit.githubuser.util.AppExecutors
import kotlinx.coroutines.launch

class FavoriteUserViewModel(private val favoriteUserRepository: FavoriteUserRepository): ViewModel() {
    fun saveUser(user: FavoriteUserEntity){
        viewModelScope.launch {
            favoriteUserRepository.setBookmarkedNews(user)
        }
    }


}
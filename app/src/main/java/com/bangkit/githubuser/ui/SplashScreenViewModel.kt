package com.bangkit.githubuser.ui

import androidx.lifecycle.*
import com.bangkit.githubuser.ui.preference.SettingPreferences

class SplashScreenViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun getThemeSetting(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    class Factory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            SplashScreenViewModel(pref) as T
    }
}
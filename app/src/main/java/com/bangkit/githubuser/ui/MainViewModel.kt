package com.bangkit.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.githubuser.data.reponse.ItemsItem
import com.bangkit.githubuser.data.reponse.SearchResponse
import com.bangkit.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){
    private val _searchUser = MutableLiveData<List<ItemsItem>>()
    val searchUser: LiveData<List<ItemsItem>> = _searchUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    companion object{
        private const val TAG = "MainViewModel"
        var USERNAME = "arif"
    }

    init {
        findUser()
    }
    fun findUser(userName: String = USERNAME){
        _isLoading.value = true
        val client: Call<SearchResponse> = ApiConfig.getApiService().getSearch(userName)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _searchUser.value = responseBody.items
                    } else {
                        _errorMessage.value = response.message()
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
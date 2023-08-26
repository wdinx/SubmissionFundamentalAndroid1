package com.bangkit.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.githubuser.data.reponse.ItemsItem
import com.bangkit.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    val listFollowing: LiveData<List<ItemsItem>> = _listFollowing

    private val _listFollowers = MutableLiveData<List<ItemsItem>>()
    val listFollowers: LiveData<List<ItemsItem>> = _listFollowers

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    companion object{
        private const val TAG = "MainViewModel"
    }

    init {
        findFollowers()
    }

    fun findFollowers(userName: String = ""){
        val following: Call<List<ItemsItem>> = ApiConfig.getApiService().getFollowes(userName)
        following.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listFollowers.value = responseBody!!
                    } else {
                        _errorMessage.value = response.message()
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _errorMessage.value = t.message
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun findFollowing(userName: String = ""){
        val following: Call<List<ItemsItem>> = ApiConfig.getApiService().getFollowing(userName)
        following.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listFollowing.value = responseBody!!
                    } else {
                        _errorMessage.value = response.message()
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _errorMessage.value = t.message
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}
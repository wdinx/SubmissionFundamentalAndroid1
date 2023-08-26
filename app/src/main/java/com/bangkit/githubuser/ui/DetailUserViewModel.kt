package com.bangkit.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    private val _detailUser = MutableLiveData<UserResponse?>()
    val  detailUser : LiveData<UserResponse?> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _username = MutableLiveData<String>()
    var username: LiveData<String> = _username

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    companion object{
        private const val TAG = "DetailUserViewModel"
    }

    init{
        findDetailUser()
    }

    fun findDetailUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(setUser())
        client.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _detailUser.value = responseBody
                    }else{
                        _errorMessage.value = response.message()
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = t.message
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun setUser(): String{
        _username.value = DetailUserActivity.USERNAME
        return _username.value!!
    }


}
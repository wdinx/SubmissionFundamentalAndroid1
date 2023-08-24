package com.bangkit.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.retrofit.ApiConfig
import com.bangkit.githubuser.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    companion object{
        var USERNAME = ""
    }

    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        USERNAME = intent.getStringExtra(USERNAME)!!

        detailUserViewModel.findDetailUser(USERNAME)

        detailUserViewModel.detailUser.observe(this){
           setDetailUser(it!!)
        }

        detailUserViewModel.isLoading.observe(this){
            showLoading(it)
        }

    }

    fun setDetailUser(userResponse: UserResponse){
        binding.tvNameDetailUser.text = userResponse.name.toString()
        binding.tvUsernameDetailUser.text = userResponse.login
        Glide.with(this)
            .load(userResponse.avatarUrl)
            .into(binding.imgDetailUser)
        binding.tvFollowers.text = getString(R.string.followers, userResponse.followers)
        binding.tvFollowing.text = getString(R.string.following, userResponse.following)
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}
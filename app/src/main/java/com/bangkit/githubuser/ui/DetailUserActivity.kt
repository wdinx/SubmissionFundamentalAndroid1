package com.bangkit.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.reponse.adapter.SectionPagerAdapter
import com.bangkit.githubuser.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object{
        var USERNAME = ""
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followersView,
            R.string.followingView
        )
    }


    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        USERNAME = intent.getStringExtra(USERNAME).toString()


        detailUserViewModel.detailUser.observe(this){
           setDetailUser(it!!)
        }

        detailUserViewModel.isLoading.observe(this){
            showLoading(it)
        }

        detailUserViewModel.errorMessage.observe(this){
            if (it != null){
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.username = USERNAME
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

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

    fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}
package com.bangkit.githubuser.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.UserResponse
import com.bangkit.githubuser.data.reponse.adapter.SectionPagerAdapter
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity
import com.bangkit.githubuser.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    companion object{
        var USERNAME = ""
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followersView,
            R.string.followingView
        )
    }


    private lateinit var binding: ActivityDetailUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        USERNAME = intent.getStringExtra(USERNAME).toString()

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val detailUserViewModel by viewModels<DetailUserViewModel>{
            factory
        }


        detailUserViewModel.detailUser.observe(this){
            setDetailUser(it!!)
            binding.tabs.visibility = View.VISIBLE
        }

        detailUserViewModel.errorMessage.observe(this){
            if (it != null){
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }

        detailUserViewModel.isLoading.observe(this){
            if (!it){
                binding.btnAdd.visibility = View.VISIBLE
            }
        }

        detailUserViewModel.getFavoriteByUser(USERNAME).observe(this){
            if (it != null){
                binding.btnAdd.changeIconColor(R.color.teal_200)
                binding.btnAdd.setOnClickListener {
                    detailUserViewModel.delete()
                }
            }else{
                binding.btnAdd.changeIconColor(R.color.white)
                binding.btnAdd.setOnClickListener {
                    detailUserViewModel.setBookmarked()
                }
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
}

private fun FloatingActionButton.changeIconColor(@ColorRes color: Int) {
    val color = ContextCompat.getColor(this.context, color)
    imageTintList = ColorStateList.valueOf(color)
}

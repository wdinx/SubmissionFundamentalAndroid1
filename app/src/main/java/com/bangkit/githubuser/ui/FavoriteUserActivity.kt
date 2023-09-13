package com.bangkit.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.adapter.FavoriteUserAdapter
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity
import com.bangkit.githubuser.databinding.ActivityFavoriteUserBinding

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: FavoriteUserViewModelFactory = FavoriteUserViewModelFactory.getInstance(this)
        val viewModel by viewModels<FavoriteUserViewModel> {
            factory
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.favorite)

        viewModel.getAllFavoriteByUser().observe(this) {
            if (it.isNullOrEmpty()) {
                binding.rvFavoriteUser.visibility = View.GONE
                binding.tvFavoriteUser.visibility = View.VISIBLE
            } else {
                setUser(it)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavoriteUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavoriteUser.addItemDecoration(itemDecoration)
    }

    private fun setUser(user: List<FavoriteUserEntity>) {
        val favoriteUserAdapter = FavoriteUserAdapter()
        favoriteUserAdapter.submitList(user)
        binding.rvFavoriteUser.adapter = favoriteUserAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
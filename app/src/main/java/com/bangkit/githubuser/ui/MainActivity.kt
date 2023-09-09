package com.bangkit.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.githubuser.data.reponse.ItemsItem
import com.bangkit.githubuser.data.reponse.adapter.UserAdapter
import com.bangkit.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration =DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener{textView, actionId, event ->
                    searchBar.text = searchView.text
                    mainViewModel.findUser(searchBar.text.toString())
                    searchView.hide()
                    false
                }
        }

        mainViewModel.searchUser.observe(this){
            setUser(it)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

        mainViewModel.errorMessage.observe(this){
            if (it != null){
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUser(user: List<ItemsItem>){
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
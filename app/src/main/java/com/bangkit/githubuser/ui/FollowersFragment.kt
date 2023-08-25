package com.bangkit.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.FollowersResponseItem
import com.bangkit.githubuser.data.reponse.adapter.FollowAdapter
import com.bangkit.githubuser.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding

    private val followersViewModel by viewModels<FollowersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followersViewModel.followers.observe(this){
            setUser(it)
        }

        val layoutManager = LinearLayoutManager(this.context)
        binding?.rvFollowers?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this.context, layoutManager.orientation)
        binding?.rvFollowers?.addItemDecoration(itemDecoration)
    }

    fun setUser(user: List<FollowersResponseItem>){
        val adapter = FollowAdapter()
        adapter.submitList(user)
        binding!!.rvFollowers.adapter = adapter
    }
}
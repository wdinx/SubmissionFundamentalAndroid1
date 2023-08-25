package com.bangkit.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.FollowersResponseItem
import com.bangkit.githubuser.data.reponse.FollowingResponseItem
import com.bangkit.githubuser.data.reponse.adapter.FollowAdapter
import com.bangkit.githubuser.data.reponse.adapter.FollowingAdapter
import com.bangkit.githubuser.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    val binding get() = _binding

    private val followingViewModel by viewModels<FollowingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            followingViewModel.following.observe(this){
                setUser(it)
            }

            val layoutManager = LinearLayoutManager(this.context)
            binding?.rvUser?.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(this.context, layoutManager.orientation)
            binding?.rvUser?.addItemDecoration(itemDecoration)
        }

        fun setUser(user: List<FollowingResponseItem>){
            val adapter = FollowingAdapter()
            adapter.submitList(user)
            binding!!.rvUser.adapter = adapter
        }
    }
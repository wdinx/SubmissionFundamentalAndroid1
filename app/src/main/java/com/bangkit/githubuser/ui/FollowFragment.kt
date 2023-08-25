package com.bangkit.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.githubuser.R
import com.bangkit.githubuser.data.reponse.ItemsItem
import com.bangkit.githubuser.data.reponse.adapter.UserAdapter
import com.bangkit.githubuser.databinding.FragmentFollowBinding
import kotlinx.coroutines.flow.combine
import kotlin.properties.Delegates

class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding

    private val followViewModel by viewModels<FollowViewModel>()


    companion object{
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = ARG_USERNAME
        var position: String = ARG_POSITION


        arguments?.let {
            position = it.getInt(ARG_POSITION).toString()
            username = it.getString(ARG_USERNAME)!!
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.rvFollow?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.rvFollow?.addItemDecoration(itemDecoration)

        followViewModel.findFollowers(username)
        followViewModel.findFollowing(username)

        if (position == "1"){
            followViewModel.listFollowers.observe(viewLifecycleOwner){
                setUser(it)
            }
        }else{
            followViewModel.listFollowing.observe(viewLifecycleOwner){
                setUser(it)
            }
        }
        followViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }
    private fun setUser(user: List<ItemsItem>){
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding?.rvFollow?.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}
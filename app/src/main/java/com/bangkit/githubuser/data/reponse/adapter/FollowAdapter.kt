package com.bangkit.githubuser.data.reponse.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubuser.data.reponse.FollowersResponseItem
import com.bangkit.githubuser.databinding.FragmentFollowersBinding
import com.bangkit.githubuser.databinding.ItemSearchBinding
import com.bangkit.githubuser.ui.DetailUserActivity
import com.bumptech.glide.Glide

class FollowAdapter: ListAdapter<FollowersResponseItem, FollowAdapter.MyViewHolder>(DIFF_CALLBAK) {

    inner class MyViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(followers: FollowersResponseItem){
            Glide.with(binding.root.context)
                .load(followers.avatarUrl)
                .into(binding.imgUser)
            binding.tvUsername.text = followers.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.USERNAME, item.login )
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object{
        val DIFF_CALLBAK = object : DiffUtil.ItemCallback<FollowersResponseItem>() {
            override fun areItemsTheSame(
                oldItem: FollowersResponseItem,
                newItem: FollowersResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FollowersResponseItem,
                newItem: FollowersResponseItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
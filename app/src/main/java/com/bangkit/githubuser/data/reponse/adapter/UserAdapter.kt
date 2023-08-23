package com.bangkit.githubuser.data.reponse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubuser.data.reponse.ItemsItem
import com.bangkit.githubuser.data.reponse.SearchResponse
import com.bangkit.githubuser.databinding.ItemSearchBinding
import com.bumptech.glide.Glide

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK){
    inner class MyViewHolder (val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemsItem){
            Glide.with(binding.root.context)
                .load(item.avatarUrl)
                .into(binding.imgUser)
            binding.tvUsername.text = item.login
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
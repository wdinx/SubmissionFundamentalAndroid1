package com.bangkit.githubuser.data.reponse.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubuser.R
import com.bangkit.githubuser.database.FavoriteUser
import com.bangkit.githubuser.databinding.ActivityDetailUserBinding
import com.bangkit.githubuser.databinding.ItemSearchBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteUserAdapter(private val onBookmarkClick: (FavoriteUser) -> Unit): androidx.recyclerview.widget.ListAdapter<FavoriteUser, FavoriteUserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder (val binding: ActivityDetailUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteUser){
            binding.tvUsernameDetailUser.text = user.username
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.imgDetailUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ActivityDetailUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object{
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteUser> =
            object : DiffUtil.ItemCallback<FavoriteUser>(){
                override fun areItemsTheSame(
                    oldItem: FavoriteUser,
                    newItem: FavoriteUser
                ): Boolean {
                    return oldItem.username == newItem.username
                }

                override fun areContentsTheSame(
                    oldItem: FavoriteUser,
                    newItem: FavoriteUser
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}
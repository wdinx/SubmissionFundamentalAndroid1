package com.bangkit.githubuser.data.reponse.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubuser.database.enitity.FavoriteUserEntity
import com.bangkit.githubuser.databinding.ItemSearchBinding
import com.bangkit.githubuser.ui.DetailUserActivity
import com.bumptech.glide.Glide

class FavoriteUserAdapter: ListAdapter<FavoriteUserEntity, FavoriteUserAdapter.MyViewHolder> (DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(user: FavoriteUserEntity){
            binding.tvUsername.text = user.login
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(binding.imgUser)
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
            intent.putExtra(DetailUserActivity.USERNAME, item.login)
            holder.itemView.context.startActivity(intent)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteUserEntity> =
            object : DiffUtil.ItemCallback<FavoriteUserEntity>() {
                override fun areItemsTheSame(oldItem: FavoriteUserEntity, newItem: FavoriteUserEntity): Boolean {
                    return oldItem.login == newItem.login
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: FavoriteUserEntity, newItem: FavoriteUserEntity): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
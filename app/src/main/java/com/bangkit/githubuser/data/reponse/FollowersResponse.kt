package com.bangkit.githubuser.data.reponse

import com.google.gson.annotations.SerializedName

data class FollowersResponse(

	@field:SerializedName("FollowersResponse")
	val followersResponse: List<FollowersResponseItem>
)

data class FollowersResponseItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("id")
	val id: Int,
)

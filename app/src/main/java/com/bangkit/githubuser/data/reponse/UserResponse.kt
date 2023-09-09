package com.bangkit.githubuser.data.reponse

import com.google.gson.annotations.SerializedName



data class UserResponse(

	@field:SerializedName("login")
	val login: String = "",

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("followers")
	val followers: Int = 0,

	@field:SerializedName("avatar_url")
	val avatarUrl: String = "",

	@field:SerializedName("following")
	val following: Int = 0,

	@field:SerializedName("name")
	val name: String? = "",
)

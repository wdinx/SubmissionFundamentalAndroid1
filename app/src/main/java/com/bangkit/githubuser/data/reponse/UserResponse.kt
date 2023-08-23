package com.bangkit.githubuser.data.reponse

import com.google.gson.annotations.SerializedName

data class UserResponse(


	@field:SerializedName("bio")
	val bio: Any? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: Any? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: Any? = null,
)

package com.bangkit.githubuser.data.retrofit

import com.bangkit.githubuser.data.reponse.*
import retrofit2.http.*
import retrofit2.Call

interface ApiService {

    @GET("search/users")
    fun getSearch(
        @Query("q") login: String
    ):Call<SearchResponse>

    @GET("users/{login}")
    fun getUser(
        @Path("login") login: String
    ):Call<UserResponse>

    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") login: String
    ):Call<FollowingResponse>

    @GET("users/{login}/followers")
    fun getFollowers(
        @Path("login") login: String
    ):Call<FollowersResponse>


}
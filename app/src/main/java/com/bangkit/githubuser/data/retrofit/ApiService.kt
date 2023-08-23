package com.bangkit.githubuser.data.retrofit

import com.bangkit.githubuser.data.reponse.FollowersResponse
import com.bangkit.githubuser.data.reponse.FollowingResponse
import com.bangkit.githubuser.data.reponse.SearchResponse
import com.bangkit.githubuser.data.reponse.UserResponse
import retrofit2.http.*
import retrofit2.Call

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users?q=udin")
    fun getSearch(
        @Query("q") login: String = "wdinx"
    ):Call<SearchResponse>

//    @Headers("Authorization: token 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")
    @GET("search/users/{username}}")
    fun getUser(
        @Path("username") login: String
    ):Call<UserResponse>

//    @Headers("Authorization: token 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")
    @GET("search/users/{username}/following")
    fun getFollowing(
        @Path("username") login: String
    ):Call<FollowingResponse>

//    @Headers("Authorization: token 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")
    @GET("search/users/{username}/followers")
    fun getFollowes(
        @Path("username") login: String
    ):Call<FollowersResponse>


}
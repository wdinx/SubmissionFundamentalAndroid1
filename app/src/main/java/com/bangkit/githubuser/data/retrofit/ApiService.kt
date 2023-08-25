package com.bangkit.githubuser.data.retrofit

import android.content.ClipData.Item
import com.bangkit.githubuser.data.reponse.*
import retrofit2.http.*
import retrofit2.Call

interface ApiService {

    @GET("search/users")
    fun getSearch(
        @Query("q") login: String
    ):Call<SearchResponse>

//    @Headers("Authorization: token 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")
    @GET("users/{login}")
    fun getUser(
        @Path("login") login: String
    ):Call<UserResponse>

//    @Headers("Authorization: token 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")
    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") login: String
    ):Call<List<ItemsItem>>

//    @Headers("Authorization: token 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")
    @GET("users/{login}/followers")
    fun getFollowes(
        @Path("login") login: String
    ):Call<List<ItemsItem>>


}
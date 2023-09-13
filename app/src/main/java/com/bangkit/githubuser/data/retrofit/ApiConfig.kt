package com.bangkit.githubuser.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val authInterceptor = Interceptor {
                val req = it.request()
                val requestHeader = req.newBuilder()
                    .addHeader("Authorization", "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")
                    .build()
                it.proceed(requestHeader)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
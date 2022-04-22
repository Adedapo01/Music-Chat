package com.codepathgroupeleven.musicchat

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
private val API_BASE_URL = "https://api.spotify.com/"
object RetrofitInstance {

    val api: PlaylistApi by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
            .create(PlaylistApi::class.java)
    }
}
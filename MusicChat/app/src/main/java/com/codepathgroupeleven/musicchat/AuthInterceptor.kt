package com.codepathgroupeleven.musicchat

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Interceptor to add auth token to requests
 */
class AuthInterceptor(context: Context) : Interceptor {
    //private val sessionManager = SessionManager(context)
    val prefs = context.getSharedPreferences("MY_APP",   Context.MODE_PRIVATE)
    private val sessionManager: SessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}
package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.spotify.sdk.android.auth.AuthorizationResponse

class MySpotifyAuthorizationActivity : AppCompatActivity() {
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = intent.data
        if (uri != null) {
            val response = AuthorizationResponse.fromUri(uri)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                }
                AuthorizationResponse.Type.ERROR -> {
                }
                else -> {
                }
            }
        }
    }
}
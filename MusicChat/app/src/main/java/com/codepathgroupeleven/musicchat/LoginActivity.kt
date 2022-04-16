package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codepathgroupeleven.musicchat.R
import com.spotify.sdk.android.auth.AccountsQueryParameters
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginBtn).setOnClickListener {

//          val REDIRECT_URI = "http://localhost:8888/callback"
            val REDIRECT_URI = "MusicChat://callback"
            var builder =
                AuthorizationRequest.Builder("4e7b34d3b9b041769632a795891d2685", AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

            builder.setScopes(arrayOf("streaming"))

            var request = builder.build()

            AuthorizationClient.openLoginInBrowser(this, request)

        }

    }



}
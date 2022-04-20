package com.codepathgroupeleven.musicchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.codepathgroupeleven.musicchat.R
import com.spotify.sdk.android.auth.AccountsQueryParameters
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginActivity : AppCompatActivity() {

    private val CLIENT_ID = "4e7b34d3b9b041769632a795891d2685"
    private val REDIRECT_URI = "musicchat://callback"
    private val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginWithSpotify()

        findViewById<Button>(R.id.loginBtn).setOnClickListener() {

            loginWithSpotify()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {

            var response = AuthorizationClient.getResponse(resultCode, data)
            var type = response.type

            if (type.equals(AuthorizationResponse.Type.TOKEN)) {

                Log.d("Login", "User Login Successful!")
                var token = response.accessToken
                goToMainActivity()
            } else if (type.equals(AuthorizationResponse.Type.ERROR)) {
                Log.e("Login", "Login Erroe!")
            } else {
                Log.d("Login", "OAuth Cancelled")
            }
        }
    }

    private fun loginWithSpotify() {
        var builder =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(Array<String>(1){"streaming"})
        var request = builder.build()
        AuthorizationClient.openLoginActivity(this,REQUEST_CODE,request)
    }

    private fun goToMainActivity() {
        val i = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }




}
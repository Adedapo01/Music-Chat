package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codepathgroupeleven.musicchat.R
import com.spotify.sdk.android.auth.AccountsQueryParameters
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse




import android.content.Intent
import android.content.SharedPreferences
import android.util.Log

private val CLIENT_ID = "ad75cbd53f034c90960dcd95a690dd9f"
private val REDIRECT_URI = "example://gizmos"
private val REQUEST_CODE = 1337
private var editor: SharedPreferences.Editor? = null
private var msharedPreferences: SharedPreferences? = null
private val SCOPES = "user-read-recently-played,user-library-modify,user-read-email,user-read-private";
class LoginActivity : AppCompatActivity() {
    lateinit var prefs : SharedPreferences//= getSharedPreferences("MY_APP",   Context.MODE_PRIVATE)
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)
        if (sessionManager.fetchAuthToken() == null || sessionManager.fetchAuthToken() == ""){
            authenticateSpotify()
            finish()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        //Log.i("Login", "${sessionManager.fetchAuthToken()}")
        //authenticateSpotify()
        //finish()
        findViewById<Button>(R.id.loginBtn).setOnClickListener {

            authenticateSpotify()
            finish()

        }

    }


    private fun authenticateSpotify() {
        val builder: AuthorizationRequest.Builder =
            AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf<String>(SCOPES))
        val request: AuthorizationRequest = builder.build()
        AuthorizationClient.openLoginInBrowser(this, request)
    }




}
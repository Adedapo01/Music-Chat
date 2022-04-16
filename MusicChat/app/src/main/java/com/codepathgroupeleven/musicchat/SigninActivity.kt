package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.codepathgroupeleven.musicchat.SigninActivity
import com.spotify.sdk.android.auth.AccountsQueryParameters.CLIENT_ID

class SigninActivity : AppCompatActivity() {
    var builder =
        AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)
    var request = builder.build()

    companion object {
        private const val REDIRECT_URI = "yourcustomprotocol://callback"
    }

    init {
        "streaming"
    }
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_signin);
    //    }
}
package com.codepathgroupeleven.musicchat

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.codepathgroupeleven.musicchat.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationResponse
import android.content.SharedPreferences
import com.google.android.material.internal.ContextUtils.getActivity


private val REQUEST_CODE = 1337
val key = ""


class MainActivity : AppCompatActivity() {
    //val bundle = Bundle()
    //private lateinit var sessionManager: SessionManager
    var token : String = "Some token From Server"
    lateinit var prefs : SharedPreferences//= getSharedPreferences("MY_APP",   Context.MODE_PRIVATE)
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var token = "Some token From Server"
        //get data (Token) back from the redirect uri after authenticating
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        Log.i("Main Activity", "data: $data")
        if (data != null) {
            var response: AuthorizationResponse = AuthorizationResponse.fromUri(data);

            when (response.getType()) {
                AuthorizationResponse.Type.TOKEN -> {
                    token = "Bearer ${response.accessToken}"
                    /*val editor = prefs.edit()
                    editor.putString(USER_TOKEN, token)
                    editor.apply()*/
                    //
                    prefs = getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
                    //prefs.edit().putString(USER_TOKEN, token).apply()
                    sessionManager = SessionManager(this)
                    sessionManager.saveAuthToken(response.accessToken)
                    //bundle.putString("token", token)*/


                }
                AuthorizationResponse.Type.ERROR -> {Log.i("Main Activity", response.error)}

            }
            // Response was successful and contains auth token

            // Handle other cases
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {

            item ->
            var fragmentToShow: Fragment? = null
            when(item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = HomeFragment()
                    //fragmentToShow.arguments = bundle
                    Toast.makeText(this, "Home is clicked!", Toast.LENGTH_LONG ).show()
                }
                R.id.action_chat -> {Toast.makeText(this, "Chat is clicked!", Toast.LENGTH_LONG ).show()}
                R.id.action_profile -> {Toast.makeText(this, "Profile is clicked!", Toast.LENGTH_LONG ).show()}
            }
            if (fragmentToShow!=null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //Return true to say that we've handled this user interaction on the item
            true
        }
        //default fragment view when opening into the app
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }


    companion object {
        const val USER_TOKEN = "user_token"
    }

}
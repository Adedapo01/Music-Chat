package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.codepathgroupeleven.musicchat.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {

            item ->
            var fragmentToShow: Fragment? = null
            when(item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = HomeFragment()
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

}
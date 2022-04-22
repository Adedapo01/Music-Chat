package com.codepathgroupeleven.musicchat


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager




/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val LOGGED_OUT = "logged_out"
    }
    /*init {
        prefs = pref
    }*/
    /**
     * Function to remove the auth token when logging out
     */
    fun removeAuthToken(){
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.apply()

    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}
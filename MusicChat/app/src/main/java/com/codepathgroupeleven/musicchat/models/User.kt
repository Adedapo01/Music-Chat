package com.codepathgroupeleven.musicchat.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import org.json.JSONObject

@Parcelize
data class User(var name : String = "",
                var email: String = "",
                var userId: String = "") : Parcelable {

    companion object {
        fun fromJson(jsonObject: JSONObject) : User {
            val user = User()
            user.name = jsonObject.getString("display_name")
            user.email = jsonObject.getString("email")
            user.userId = jsonObject.getString("id")

            return user
        }
    }
                }
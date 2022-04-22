package com.codepathgroupeleven.musicchat.models

import android.os.Parcelable
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize

import org.json.JSONObject

@Parcelize
data class User(var name : String = "",
                var email: String = "",
                var userId: String = "",
                var userImage: String="") : Parcelable {

    companion object {
        fun fromJson(jsonObject: JsonObject?) : User {
            val user = User()
            user.name = jsonObject!!["display_name"].asString
            user.email = jsonObject!!["email"].asString
            user.userId = jsonObject!!["id"].asString
            user.userImage = jsonObject!!["images"].asJsonArray[0].asJsonObject["url"].asString

            return user
        }
    }
                }
package com.codepathgroupeleven.musicchat.models

import android.os.Parcelable
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize

@Parcelize
data class Playlist (
    var name: String = "",
    var id: String = "",
    var image: String? = "") : Parcelable {


    companion object {
        fun fromJson(jsonObject: JsonObject) : Playlist {
            val playlist = Playlist()
            playlist.name = jsonObject["name"].asString
            playlist.id = jsonObject["id"].asString
            playlist.image = jsonObject["images"].asJsonArray.get(0).asJsonObject["url"].asString

            return playlist
        }

        fun fromJsonArray(jsonArray: JsonArray?) : MutableList<Playlist> {
            val playlists = ArrayList<Playlist>()
            for (i in 0 until jsonArray!!.size()){
                playlists.add(fromJson(jsonArray.get(i).asJsonObject))
            }
            return playlists
        }

    }
}
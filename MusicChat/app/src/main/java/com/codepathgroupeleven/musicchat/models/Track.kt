package com.codepathgroupeleven.musicchat.models


import android.os.Parcelable
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track (
    var artistName: String = "",
    var trackId: String = "",
    var image: String? = "",
    var duration: Double? = 0.0) : Parcelable {


    companion object {
        fun fromJson(jsonObject: JsonObject) : Track {
            val track = Track()
            //var isTrack = jsonObject.getAsJsonObject("track").asJsonObject["track"].asBoolean

            track.artistName = jsonObject.getAsJsonObject("track").asJsonObject.getAsJsonArray("artists")[0].asJsonObject["name"].asString
            track.trackId = jsonObject.getAsJsonObject("track").asJsonObject["id"].asString
            track.image = jsonObject.getAsJsonObject("track").asJsonObject.getAsJsonObject("album").getAsJsonArray("images").get(0).asJsonObject["url"].asString
            var dur = jsonObject.getAsJsonObject("track").asJsonObject["duration_ms"].asLong
            track.duration = (dur * 0.001) * 0.0166667



            return track
        }

        fun fromJsonArray(jsonArray: JsonArray?) : MutableList<Track> {
            val tracks = ArrayList<Track>()
            for (i in 0 until jsonArray!!.size()){
                tracks.add(fromJson(jsonArray.get(i).asJsonObject))
            }
            return tracks
        }

    }
}
package com.codepathgroupeleven.musicchat

import com.codepathgroupeleven.musicchat.models.Playlist
import com.codepathgroupeleven.musicchat.models.User
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PlaylistApi {

    //@Header("Authorization") token: String

    @GET("/v1/me/playlists")
    suspend fun getAllPlaylists(): Response<JsonObject>
    @GET("/v1/playlists/{playlist_id}/tracks")
    suspend fun getAllTracks(@Path(value="playlist_id", encoded=false)  playlist_id: String): Response<JsonObject>
    @GET("/v1/me")
    suspend fun getUserProfile(): Response<JsonObject>
}
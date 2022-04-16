package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.codepathgroupeleven.musicchat.PlaylistAdapter.Companion.playlistTracks
import com.codepathgroupeleven.musicchat.fragments.HomeFragment
import com.codepathgroupeleven.musicchat.models.Playlist
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "TracksActivity"
class TracksActivity : AppCompatActivity() {
    lateinit var playlistId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)

        val playlist = intent.getParcelableExtra<Playlist>(playlistTracks) as Playlist
        Log.i(TAG, "playlist is ${playlist.id}")
        playlistId = playlist.id
        getAllTracks()
    }

    fun getAllTracks() {

        lifecycleScope.launchWhenCreated {

            val response = try{
                RetrofitInstance.api.getAllTracks(token =token,  playlist_id = playlistId)

            } catch(e: IOException){
                Log.e(TAG, "IOException")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException")
                return@launchWhenCreated
            }catch (e: Exception){
                Log.e(TAG,"Error: $e")
                return@launchWhenCreated
            }
            Log.i(TAG, "$response")
            if (response.isSuccessful && response.body() != null) {
                Log.i(TAG, "Successful")
                val gson = Gson()
                Log.i(TAG, "Playlist Tracks: ${response.body()}")

                var jsonObject = response.body()
                var name = jsonObject?.asJsonObject!!.getAsJsonArray("items")[0].asJsonObject.getAsJsonObject("track").asJsonObject.getAsJsonArray("artists")[0].asJsonObject["name"].asString
                Log.i(TAG, name)
            /*var it = items?.getAsJsonArray("items")

                allPlaylists.addAll(Playlist.fromJsonArray(it))
                adapter.notifyDataSetChanged()
                Log.i(HomeFragment.TAG, "playlist: $allPlaylists")*/
            }
        }

    }
    companion object{
        private val token = "Bearer BQCPB6tNjW84CpY1qpbN8N-9theL7WR69y09vXLmzUoYLXsqwhEWAKEJb5kedKusemciyJB63Rd2EKS2lbEPKIkwYvwIKS1L0QwMIso7uhSnuIN-lBxAJmT1t876lNO1dqMDljTEPuD9LaSboKxqA1TkQfyoajT-WA6ulWNqO7Bf_A9AhAKS-DpCg6Zpf-N5Jg"

    }
}
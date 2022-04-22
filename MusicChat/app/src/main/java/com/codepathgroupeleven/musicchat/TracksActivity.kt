package com.codepathgroupeleven.musicchat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.codepathgroupeleven.musicchat.PlaylistAdapter.Companion.playlistTracks
import com.codepathgroupeleven.musicchat.fragments.HomeFragment
import com.codepathgroupeleven.musicchat.models.Playlist
import com.codepathgroupeleven.musicchat.models.Track
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "TracksActivity"
class TracksActivity : AppCompatActivity() {
    lateinit var playlistId: String
    lateinit var token : String
    //val apiClient: ApiClient = ApiClient(this)
    var tracks: MutableList<Track> = mutableListOf()
    //val sessionManager = SessionManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)
        /*val sharedPreferences = this.getSharedPreferences("MY_APP",   Context.MODE_PRIVATE) // kotlin

        token = sharedPreferences.getString("TOKEN","").toString()*/
        val playlist = intent.getParcelableExtra<Playlist>(playlistTracks) as Playlist
        Log.i(TAG, "playlist is ${playlist.id}")
        playlistId = playlist.id
        //getAllTracks()
    }

    /*fun getAllTracks() {

        lifecycleScope.launchWhenCreated {

            val response = try{
                RetrofitInstance.api.getAllTracks(token = sessionManager.fetchAuthToken().toString(), playlist_id = playlistId)

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
                var items = jsonObject?.asJsonObject!!.getAsJsonArray("items")
                //Log.i(TAG, name)
                tracks.addAll(Track.fromJsonArray(items))
                Log.i(TAG, "List of Tracks: $tracks")
            *//*var it = items?.getAsJsonArray("items")


                adapter.notifyDataSetChanged()
                Log.i(HomeFragment.TAG, "playlist: $allPlaylists")*//*
            }
        }

    }*/
    companion object{
/*
        private val token = "Bearer BQB7wx5FlmhdZdoP5KoSFeTnXwwDTcqqRh0qU_ZlKo0hg14zejXAWEuKvk-nh_GK37MvCJpUFaDL-1vNDHdd7NMCNDI5lOGj7MlxtI6MmRyo-apoYHNy3dhAzlFo6sfXYhOgx1KKJYceYv9diDheBDGN4ZsnLkp8aNl3q7fWLk1U9Yumjtgl3gbKoxGhDFlqprgFA12EycsMT2Z22cIOOZmnC9duiug5Dlr506phbKXnIeyFPPPRAxYE2xS2Dyew7Dn5zXqG7osPLvcHzlcsMkC_ge9i6-qz6hUGTec-Gb3su4AU"
*/

    }
}
package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.codepathgroupeleven.musicchat.models.Playlist
import com.codepathgroupeleven.musicchat.models.Song
import com.codepathgroupeleven.musicchat.models.Track

class MediaPlayerActivity : AppCompatActivity() {

    lateinit var trackInfo: TextView
    lateinit var trackImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        trackInfo = findViewById(R.id.musicInfo)
        trackImage = findViewById(R.id.musicCover)

        val song = intent.getParcelableExtra<Track>("song") as Track
        Log.i(TAG, "song is ${song.trackId}")
        trackInfo.text = song.trackName + " - " + song.artistName

        Glide.with(applicationContext).load(song.image).centerCrop().into(trackImage)



//        getSong()
    }

//    fun getSong() {
//        TODO("Not yet implemented")
//    }

    companion object{
        private val TAG = "MediaPlayer"
    }

}
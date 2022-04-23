package com.codepathgroupeleven.musicchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MediaPlayerActivity : AppCompatActivity() {

    lateinit var trackInfo: TextView
    lateinit var trackImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        trackInfo = findViewById(R.id.musicInfo)
        trackImage = findViewById(R.id.musicCover)
////       val song = intent.getParcelableExtra<Song> as Song
//        val songInfo = intent.getExtras()
//        Log.i(TAG, "song is $songInfo")

//        getSong()
    }

//    fun getSong() {
//        TODO("Not yet implemented")
//    }

    companion object{
        private val TAG = "MediaPlayer"
    }

}
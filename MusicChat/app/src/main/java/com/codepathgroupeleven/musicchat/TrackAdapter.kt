package com.codepathgroupeleven.musicchat

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepathgroupeleven.musicchat.PlaylistAdapter.Companion.playlistTracks
import com.codepathgroupeleven.musicchat.models.Track

class TrackAdapter(val context: Context, val tracks: List<Track>)
    : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_track, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks.get(position)
        holder.bind(track)

    }

    override fun getItemCount() = tracks.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val trackImage = itemView.findViewById<ImageView>(R.id.trackImage)
        val trackArtist = itemView.findViewById<TextView>(R.id.trackArtist)
        val trackDuration = itemView.findViewById<TextView>(R.id.trackDuration)
        var trackTitle = itemView.findViewById<TextView>(R.id.username)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(track: Track) {
            trackArtist.text = track.artistName
            trackTitle.text = track.trackName
            trackDuration.text = track.duration
            Glide.with(itemView.context).load(track.image).into(trackImage)
        }

        override fun onClick(view: View?) {
            //Toast.makeText(this, "${tracks[adapterPosition]}", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "${tracks[adapterPosition]}")
            /*val playlist = playlists[adapterPosition]
            //use the intent system to navigate to tracksactivity when a playlist is clicked
            val intent = Intent(view!!.getContext(), TracksActivity::class.java)
            intent.putExtra(playlistTracks, playlist)
            view.getContext().startActivity(intent)*/

            val mediaInfo = tracks[adapterPosition]
            Log.i("Media Info", "$mediaInfo")
            //use the intent system to navigate to tracksactivity when a playlist is clicked
            val intent = Intent(view!!.getContext(), MediaPlayerActivity::class.java)
           intent.putExtra("song", mediaInfo)
            view.getContext().startActivity(intent)



        }
//        val intent = Intent(view!!.getContext(), TracksActivity::class.java)
//        val songInfo = {tracks[adapterPosition]}
//        Intent(this, MediaPlayerActivity::class.java)
//        startActivity(intent)
    }


    companion object{
        //val playlistTracks = "playlistTracks"
        val TAG = "TrackAdapter"
    }
}
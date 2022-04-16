package com.codepathgroupeleven.musicchat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepathgroupeleven.musicchat.models.Playlist

class PlaylistAdapter(val context: Context, val playlists: List<Playlist>)
    : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_playlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlists.get(position)
        holder.bind(playlist)
    }

    override fun getItemCount() = playlists.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val playlistImage = itemView.findViewById<ImageView>(R.id.playlistImage)
        val playlistTitle = itemView.findViewById<TextView>(R.id.playlistTitle)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(playlist: Playlist) {
            playlistTitle.text = playlist.name
            Glide.with(itemView.context).load(playlist.image).into(playlistImage)
        }

        override fun onClick(view: View?) {
            val playlist = playlists[adapterPosition]
            //use the intent system to navigate to tracksactivity when a playlist is clicked
            val intent = Intent(view!!.getContext(), TracksActivity::class.java)
            intent.putExtra(playlistTracks, playlist)
            view.getContext().startActivity(intent)
        }
    }

    companion object{
        val playlistTracks = "playlistTracks"
    }
}

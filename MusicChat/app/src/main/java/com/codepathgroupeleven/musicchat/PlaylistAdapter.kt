package com.codepathgroupeleven.musicchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playlistImage = itemView.findViewById<ImageView>(R.id.playlistImage)
        val playlistTitle = itemView.findViewById<TextView>(R.id.playlistTitle)

        fun bind(playlist: Playlist) {
            playlistTitle.text = playlist.name
            Glide.with(itemView.context).load(playlist.image).into(playlistImage)
        }
    }
}

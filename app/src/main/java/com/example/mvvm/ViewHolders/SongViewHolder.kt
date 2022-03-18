package com.example.mvvm.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.Song

class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var song: Song? = null

    val nameTV: TextView = itemView.findViewById(R.id.songName)
    val albumTV: TextView = itemView.findViewById(R.id.songAlbum)

    fun bindUser(song: Song) {
        this.song = song
        nameTV.text = song.title
        albumTV.text = song.album
    }


}
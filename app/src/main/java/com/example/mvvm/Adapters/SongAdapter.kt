package com.example.mvvm.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.Song
import com.example.mvvm.ViewHolders.SongViewHolder

class SongAdapter :RecyclerView.Adapter<SongViewHolder> () {

    private val songs = ArrayList<Song>()

    init {
        songs.add(Song("nombre de cancion","nombre del album"))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //XML -> View
        val view = inflater.inflate(R.layout.song_view, parent, false)
        val songViewHolder = SongViewHolder(view)
        return songViewHolder
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {

        val song = songs[position]
        holder.bindUser(song)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun addUser(user: Song) {
        songs.add(user)
        notifyItemInserted(songs.size-1)
    }

    fun clear() {
        val size = songs.size
        songs.clear()
        notifyItemRangeRemoved(0, size)
    }


}
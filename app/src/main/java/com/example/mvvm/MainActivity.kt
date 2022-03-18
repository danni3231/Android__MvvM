package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.Adapters.SongAdapter
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val songListViewModel: SongListViewModel by viewModels()
    private lateinit var songAdapter: SongAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        songAdapter = SongAdapter()
        binding.songList.adapter = songAdapter
        binding.songList.layoutManager = LinearLayoutManager(this)


        binding.searchBtn.setOnClickListener {
            var artist: String = binding.artistName.toString()

            Log.e(">>>", artist)
            songListViewModel.GETListSongs("mana")

        }

        //Observa las variables mutables de SongListViewModel
        songListViewModel.songList.observe(this) {
            songAdapter.clear()
            it.forEach {
                songAdapter.addUser(it)
            }
        }
    }
}
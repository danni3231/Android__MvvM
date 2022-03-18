package com.example.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SongListViewModel : ViewModel() {

    //Variable que "actualiza la info"
    private var _songList: MutableLiveData<ArrayList<Song>> = MutableLiveData()

    //polimorfismo
    val songList: LiveData<ArrayList<Song>>
        get() {
            return _songList
        }

    val gson: Gson = Gson()

    //endpoint
    fun GETListSongs(search: String) {


        //entra al hilo para hacer el llamado
        viewModelScope.launch(Dispatchers.IO) {

            //conecta el servidor
            val url = URL("https://api.deezer.com/search?q=${search}")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val json = connection.inputStream.bufferedReader().readText()

            //serializa
            var deezerObjt = gson.fromJson(json, DeezerObjt::class.java)
            val songs = ArrayList<Song>()
            deezerObjt.data.forEach {
                songs.add(Song(it.title,it.album.title))
            }

            //accede al hilo de la vista
            withContext(Dispatchers.Main) {
                _songList.value = songs
            }

        }
    }

    //estructurar el objecto que resive
    data class DeezerObjt(
        var data: ArrayList<Track>
    )

    data class Track(
        var title: String,
        var album: Album
    )

    data class Album(
        var title: String
    )
}
package com.example.vinilos.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.Album
import com.example.vinilos.models.Artista
import com.example.vinilos.models.Collector
import com.example.vinilos.models.Tracks
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://vynils-back-heroku.herokuapp.com/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getAlbums(): List<Album> {
        val suspendCoroutine = suspendCoroutine<List<Album>> { cont ->
            requestQueue.add(
                getRequest("albums",
                    { response ->
                        val resp = JSONArray(response)
                        val list = mutableListOf<Album>()
                        var item:JSONObject?
                        var tracksObjectArray: JSONArray?
                        var trackList: MutableList<Tracks>
                        var value:Tracks?
                        for (i in 0 until resp.length()) {
                            item = resp.getJSONObject(i)
                            tracksObjectArray = item.getJSONArray("tracks")
                            trackList = mutableListOf<Tracks>()

                            for (a in 0 until tracksObjectArray.length()) {
                                value = Tracks(
                                    tracksObjectArray.getJSONObject(a).getInt("id"),
                                    tracksObjectArray.getJSONObject(a).getString("name"),
                                    tracksObjectArray.getJSONObject(a).getString("duration")
                                )
                                trackList.add(a, value)
                            }

                            list.add(
                                i,
                                Album(
                                    item.getInt("id"),
                                    item.getString("name"),
                                    item.getString("cover"),
                                    item.getString("releaseDate"),
                                    item.getString("description"),
                                    item.getString("genre"),
                                    item.getString("recordLabel"),
                                    trackList
                                )
                            )
                        }
                        cont.resume(list)
                    },
                    {
                        cont.resumeWithException(it)
                    })
            )
        }
        return suspendCoroutine
    }

    suspend fun getArtistas(): List<Artista> {
        val suspendCoroutine = suspendCoroutine<List<Artista>> { cont ->
            requestQueue.add(
                getRequest("musicians",
                    { response ->
                        val resp = JSONArray(response)
                        val list = mutableListOf<Artista>()
                        var item:JSONObject?
                        for (i in 0 until resp.length()) {
                            item = resp.getJSONObject(i)
                            list.add(
                                i,
                                Artista(
                                    item.getInt("id"),
                                    item.getString("name"),
                                    item.getString("image"),
                                    item.getString("description"),
                                    item.getString("birthDate"),
                                )
                            )
                        }
                        cont.resume(list)
                    },
                    {
                        cont.resumeWithException(it)
                    })
            )
        }
        return suspendCoroutine
    }

    suspend fun getArtistasByCollector(collectorId: Int): List<Artista> {
        val suspendCoroutine = suspendCoroutine<List<Artista>> { cont ->
            requestQueue.add(
                getRequest("collectors/$collectorId/performers",
                    { response ->
                        val resp = JSONArray(response)
                        val list = mutableListOf<Artista>()
                        var item:JSONObject?
                        for (i in 0 until resp.length()) {
                            item = resp.getJSONObject(i)
                            list.add(
                                i,
                                Artista(
                                    item.getInt("id"),
                                    item.getString("name"),
                                    item.getString("image"),
                                    item.getString("description"),
                                    ""
                                    //item.getString("birthDate"),
                                )
                            )
                        }
                        cont.resume(list)
                    },
                    {
                        cont.resumeWithException(it)
                    })
            )
        }
        return suspendCoroutine
    }


    suspend fun getCollectors() = suspendCoroutine<List<Collector>>{ cont->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                var item:JSONObject?
                for (i in 0 until resp.length()) { //inicializado como variable de retorno
                    item = resp.getJSONObject(i)
                    list.add(Collector(collectorId = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }
    suspend fun getAlbum(albumId:Int) = suspendCoroutine<Album>{ cont->
        requestQueue.add(getRequest("albums/$albumId",
            { response ->
                val item = JSONObject(response)
                val tracksObjectArray = item.getJSONArray("tracks")
                val trackList = mutableListOf<Tracks>()
                var value:Tracks?
                for (a in 0 until tracksObjectArray.length()) {
                    value = Tracks(
                        tracksObjectArray.getJSONObject(a).getInt("id"),
                        tracksObjectArray.getJSONObject(a).getString("name"),
                        tracksObjectArray.getJSONObject(a).getString("duration")
                    )
                    trackList.add(a, value)
                }
                val album:Album = (Album(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getString("cover"),
                            item.getString("releaseDate"),
                            item.getString("description"),
                            item.getString("genre"),
                            item.getString("recordLabel"),
                            trackList
                        ))
                cont.resume(album)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getArtista(artistaId:Int) = suspendCoroutine<Artista>{ cont->
        requestQueue.add(getRequest("musicians/$artistaId",
            { response ->
                val item = JSONObject(response)
                Log.d("Response Artista", item.toString())
                val artista:Artista = (Artista(
                    item.getInt("id"),
                    item.getString("name"),
                    item.getString("image"),
                    item.getString("description"),
                    item.getString("birthDate"),
                ))
                cont.resume(artista)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollector(collectorId:Int) = suspendCoroutine<Collector>{ cont->
        requestQueue.add(getRequest("collectors/$collectorId",
            { response ->
                val item = JSONObject(response)
                Log.d("Response Artista", item.toString())
                val collector:Collector = (Collector(
                    item.getInt("id"),
                    item.getString("name"),
                    item.getString("telephone"),
                    item.getString("email")
                ))
                cont.resume(collector)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}
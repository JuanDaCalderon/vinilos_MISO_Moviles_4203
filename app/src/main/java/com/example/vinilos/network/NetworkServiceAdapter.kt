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
                        for (i in 0 until resp.length()) {
                            item = resp.getJSONObject(i)
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
                Log.d("Response Album", item.toString())
                val album:Album = (Album(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getString("cover"),
                            item.getString("releaseDate"),
                            item.getString("description"),
                            item.getString("genre"),
                            item.getString("recordLabel"),
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

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}
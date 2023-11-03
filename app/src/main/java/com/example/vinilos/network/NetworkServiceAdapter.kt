package com.example.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.modelos.Album
import org.json.JSONArray

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://vynils-back-heroku.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
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

    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest(
            "albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val listTracks = Array(item.getJSONArray("tracks").length()) {
                        item.getJSONArray("tracks").getString(it)
                    }

                    val listPerformers = Array(item.getJSONArray("performers").length()) {
                        item.getJSONArray("performers").getString(it)
                    }

                    val listComments = Array(item.getJSONArray("comments").length()) {
                        item.getJSONArray("comments").getString(it)
                    }
                    list.add(i,
                        Album(
                            item.getInt("id"),
                            item.getString("name"),
                            item.getString("cover"),
                            item.getString("releaseDate"),
                            item.getString("description"),
                            item.getString("genre"),
                            item.getString("recordLabel"),
                            listTracks,
                            listPerformers,
                            listComments,
                            )
                    )
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener, errorListener)
    }
}
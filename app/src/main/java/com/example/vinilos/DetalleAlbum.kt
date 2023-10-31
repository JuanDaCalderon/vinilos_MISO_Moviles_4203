package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vinilos.broker.VolleyBroker



class DetalleAlbum : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_album)

        volleyBroker = VolleyBroker(this.applicationContext)

        val getButton: Button = findViewById(R.id.get_albums_button)
        val getResultTextView : TextView = findViewById(R.id.get_result_text)
        getButton.setOnClickListener {

            val idAlbum: EditText = findViewById(R.id.id_album)
            val album = idAlbum.text

            volleyBroker.instance.add(VolleyBroker.getRequest("albums/" + album,
                { response ->
                    // Display the first 500 characters of the response string.
                    getResultTextView.text = "Response is: ${response}"
                },
                {
                    Log.d("TAG", it.toString())
                    getResultTextView.text = it.toString()
                }
            ))
        }
    }
}
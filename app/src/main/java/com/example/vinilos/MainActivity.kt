package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Response
import com.example.vinilos.broker.VolleyBroker
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volleyBroker = VolleyBroker(this.applicationContext)

        val postButton: Button = findViewById(R.id.post_album_button)
        val postResultTextView : TextView = findViewById(R.id.get_result_text)
        postButton.setOnClickListener {

            val nameTxt: EditText = findViewById(R.id.album_nombre)
            val coverTxt: EditText = findViewById(R.id.album_cover)
            val fechaLanzamientoTxt: EditText = findViewById(R.id.album_fecha_lanzamiento)
            val descripcionTxt: EditText = findViewById(R.id.album_descripcion)
            val generoTxt: EditText = findViewById(R.id.album_genero)
            val disqueraTxt: EditText = findViewById(R.id.album_disquera)
            val postParams = mapOf<String, Any>(
                "nombre" to nameTxt.text.toString(),
                "cover" to coverTxt.text.toString(),
                "fechaLanzamiento" to fechaLanzamientoTxt.text.toString(),
                "descripcion" to descripcionTxt.text.toString(),
                "genero" to generoTxt.text.toString(),
                "disquera" to disqueraTxt.text.toString()
            )
            volleyBroker.instance.add(VolleyBroker.postRequest("albums", JSONObject(postParams),
                Response.Listener<JSONObject> { response ->
                    // Display the first 500 characters of the response string.
                    postResultTextView.text = "Response is: ${response.toString()}"
                },
                Response.ErrorListener {
                    Log.d("TAG", it.toString())
                    postResultTextView.text = "That didn't work!"
                }
            ))
        }
    }
}
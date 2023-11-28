package com.example.vinilos.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vinilos.R
import com.example.vinilos.network.VolleyBroker
import org.json.JSONObject


class AsociarTracksFragment : AppCompatActivity() {
    private lateinit var volleyBroker: VolleyBroker

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_asociar_track)
        volleyBroker = VolleyBroker(this.applicationContext)
        val postButton: Button = findViewById(R.id.post_track_button)
        val postResultTextView : TextView = findViewById(R.id.get_result_text)

        postButton.setOnClickListener {

            val nameTxt: EditText = findViewById(R.id.track_nombre)
            val durationTxt: EditText = findViewById(R.id.track_duration)
            val albumIdTxt: EditText = findViewById(R.id.album_id_asociar_text)

            val postParams = mapOf<String, Any>(
                "name" to nameTxt.text.toString(),
                "duration" to durationTxt.text.toString(),
            )

            val albumId = albumIdTxt.text.toString()

            if (validarCampos(nameTxt,durationTxt)) {
                volleyBroker.instance.add(VolleyBroker.postRequestTracks("albums/$albumId/tracks", JSONObject(postParams),
                    { response ->
                        // Display the first 500 characters of the response string.
                        postResultTextView.text = "Response is: ${response.toString()}"
                        Toast.makeText(applicationContext, "Track asociado al Album $albumId", Toast.LENGTH_SHORT).show()

                    },
                    {
                        Log.d("TAG", it.toString())
                        postResultTextView.text = it.toString()
                        Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                ))
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            }
        }

        val cancelButton: Button = findViewById(R.id.cancel_track_button)
        cancelButton.setOnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }

    private fun validarCampos(name:EditText,  duration:EditText ):Boolean {
        var isValid = true
        if (name.text.toString().isBlank()) {
            isValid = false
            name.error = getString(R.string.form_required_field)
        }
        if (duration.text.toString().isBlank()) {
            isValid = false
            duration.error = getString(R.string.form_required_field)
        }
        return isValid
    }
}
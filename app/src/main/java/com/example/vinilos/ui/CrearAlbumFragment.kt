package com.example.vinilos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Response
import com.example.vinilos.R
import com.example.vinilos.network.VolleyBroker
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import android.widget.Toast

class CrearAlbumFragment : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_crear_album)

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
                "name" to nameTxt.text.toString(),
                "cover" to coverTxt.text.toString(),
                "releaseDate" to fechaLanzamientoTxt.text.toString(),
                "description" to descripcionTxt.text.toString(),
                "genre" to generoTxt.text.toString(),
                "recordLabel" to disqueraTxt.text.toString()
            )

            if (validarCampos(nameTxt,coverTxt, fechaLanzamientoTxt, descripcionTxt,generoTxt, disqueraTxt )) {
                volleyBroker.instance.add(VolleyBroker.postRequest("albums", JSONObject(postParams),
                    Response.Listener<JSONObject> { response ->
                        // Display the first 500 characters of the response string.
                        postResultTextView.text = "Response is: ${response.toString()}"
                        Toast.makeText(applicationContext, getString(R.string.album_creado_text), Toast.LENGTH_SHORT).show()

                    },
                    Response.ErrorListener {
                        Log.d("TAG", it.toString())
                        postResultTextView.text = it.toString()
                        Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                ))
            }
        }

        val fechaLanzamientoET: EditText = findViewById(R.id.album_fecha_lanzamiento)
        fechaLanzamientoET.setOnClickListener {showDatePickerDialog()}

        val cancelButton: Button = findViewById(R.id.cancel_album_button)
            cancelButton.setOnClickListener {
                val mainActivity = Intent(applicationContext, MainActivity::class.java)
                startActivity(mainActivity)
            }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        val fechaLanzamientoET: EditText = findViewById(R.id.album_fecha_lanzamiento)
        fechaLanzamientoET.setText("$day/$month/$year")
    }

    private fun validarCampos(name:EditText,  cover:EditText, releaseDate:EditText, description:EditText, genre:EditText, recordLabel:EditText):Boolean {
        var isValid = true

        if (name.text.toString().isBlank()) {
            isValid = false
            name.error = getString(R.string.form_required_field)
        }
        if (cover.text.toString().isBlank()) {
            isValid = false
            cover.error = getString(R.string.form_required_field)
        }
        if (releaseDate.text.toString().isBlank()) {
            isValid = false
            releaseDate.error = getString(R.string.form_required_field)
        }
        if (description.text.toString().isBlank()) {
            isValid = false
            description.error = getString(R.string.form_required_field)
        }
        if (genre.text.toString().isBlank()) {
            isValid = false
            genre.error = getString(R.string.form_required_field)
        }
        if (recordLabel.text.toString().isBlank()) {
            isValid = false
            recordLabel.error = getString(R.string.form_required_field)
        }

        return isValid
    }

    private fun resetCampos(name:EditText,  cover:EditText, releaseDate:EditText, description:EditText, genre:EditText, recordLabel:EditText) {
        name.setText("")
        cover.setText("")
        releaseDate.setText("")
        description.setText("")
        genre.setText("")
        recordLabel.setText("")

    }

}
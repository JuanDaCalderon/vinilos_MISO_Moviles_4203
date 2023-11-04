package com.example.vinilos.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.example.vinilos.R
import com.example.vinilos.network.VolleyBroker
import org.json.JSONObject


class CrearAlbumFragment : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_crear_album)

        volleyBroker = VolleyBroker(this.applicationContext)

        val postButton: Button = findViewById(R.id.post_album_button)
        val postResultTextView : TextView = findViewById(R.id.get_result_text)
        val spinnerGenres:Spinner = findViewById(R.id.spinner_genre)
        val spinnerRecordLabel:Spinner = findViewById(R.id.spinner_recordLabel)

        val adapterGenres = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item)
        val adapterRecordLabel = ArrayAdapter.createFromResource(this, R.array.recordLabels, android.R.layout.simple_spinner_item)
        adapterGenres.setDropDownViewResource(android.R.layout.simple_spinner_item)
        adapterRecordLabel.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerGenres.setAdapter(adapterGenres)
        spinnerRecordLabel.setAdapter(adapterRecordLabel)

        postButton.setOnClickListener {

            val nameTxt: EditText = findViewById(R.id.album_nombre)
            val coverTxt: EditText = findViewById(R.id.album_cover)
            val fechaLanzamientoTxt: EditText = findViewById(R.id.album_fecha_lanzamiento)
            val descripcionTxt: EditText = findViewById(R.id.album_descripcion)
            val genre:String = spinnerGenres.selectedItem.toString()
            val recordLabel:String = spinnerRecordLabel.selectedItem.toString()
            val postParams = mapOf<String, Any>(
                "name" to nameTxt.text.toString(),
                "cover" to coverTxt.text.toString(),
                "releaseDate" to fechaLanzamientoTxt.text.toString(),
                "description" to descripcionTxt.text.toString(),
                "genre" to genre,
                "recordLabel" to recordLabel
            )

            if (validarCampos(nameTxt,coverTxt, fechaLanzamientoTxt, descripcionTxt)) {
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
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
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

    private fun validarCampos(name:EditText,  cover:EditText, releaseDate:EditText, description:EditText):Boolean {
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
        return isValid
    }
}
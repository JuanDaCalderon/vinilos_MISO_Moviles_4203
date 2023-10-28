package com.example.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vinilos.broker.VolleyBroker



class MainActivity : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volleyBroker = VolleyBroker(this.applicationContext)

        val getButton: Button = findViewById(R.id.get_albums_button)
        val getResultTextView : TextView = findViewById(R.id.get_result_text)
        getButton.setOnClickListener {

            volleyBroker.instance.add(VolleyBroker.getRequest("albums",
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
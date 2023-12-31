package com.example.chiwa_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0

        var mainText = findViewById<TextView>(R.id.mainText)
        var tapHere = findViewById<Button>(R.id.tapHere)

        tapHere.setOnClickListener {
            mainText.setText(R.string.counterName)
        }
    }
}
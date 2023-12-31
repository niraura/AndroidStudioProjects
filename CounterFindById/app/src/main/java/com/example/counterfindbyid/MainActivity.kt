package com.example.counterfindbyid

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

        val numberText = findViewById<TextView>(R.id.NumberText)
        val addButton = findViewById<Button>(R.id.AddButton)
        val difButton = findViewById<Button>(R.id.DifButton)

        addButton.setOnClickListener{
            count++
            numberText.text = count.toString()
            Log.i("＋ボタンが押されました", count.toString())
        }

        difButton.setOnClickListener{
            count--
            numberText.text = count.toString()
            Log.i("－ボタンが押されました", count.toString())
        }


    }
}
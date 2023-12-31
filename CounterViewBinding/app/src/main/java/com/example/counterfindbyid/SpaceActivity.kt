package com.example.counterfindbyid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.counterfindbyid.databinding.ActivitySpaceBinding

class SpaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpaceBinding //Binding追記[2]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpaceBinding.inflate(layoutInflater) //Binding追記[3]
        val view = binding.root //Binding追記[4]
        setContentView(view) //Binding追記[5]

        // backボタンが押された
        binding.backButton.setOnClickListener {
            // 次のアクティビティに遷移する
            finish()
        }
    }
}
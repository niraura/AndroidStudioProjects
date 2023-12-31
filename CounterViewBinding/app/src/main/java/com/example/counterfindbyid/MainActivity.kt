package com.example.counterfindbyid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.counterfindbyid.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //Binding追記[2]
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //Binding追記[3]
        val view = binding.root //Binding追記[4]
        //setContentView(R.layout.activity_main) //Binding削除
        setContentView(view) //Binding追記[5]

        firebaseAnalytics = Firebase.analytics
        var count = 0

        //＋ボタンが押された
        binding.addButton.setOnClickListener {
            count++
            binding.NumberText.text = count.toString()
            Log.i("＋ボタンが押されました", count.toString())

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_ID, 1)
                param(FirebaseAnalytics.Param.ITEM_NAME, "abc")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            }
        }
        //－ボタンが押された
        binding.difButton.setOnClickListener {
            count--
            binding.NumberText.text = count.toString()
            Log.i("－ボタンが押されました", count.toString())
        }

        // warpボタンが押された
        binding.warpButton.setOnClickListener {
            // 次のアクティビティに遷移する
            val intent = Intent(this@MainActivity, SpaceActivity::class.java)
            startActivity(intent)
        }
    }

    public override fun onResume() {
        super.onResume()
        recordScreenView()
    }

    private fun recordScreenView() {
        val screenName = "Main Page"

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }
}
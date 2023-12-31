/*
 * Copyright 2019 Brother Industries, Ltd.
 */
package com.brother.mfc.shenlong.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.brother.mfc.shenlong.R
import com.brother.mfc.shenlong.databinding.ActivityMainBinding
import com.brother.mfc.shenlong.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * メイン画面
 */
class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    /**
     * レイアウトとバインディングを行う
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                vm = this@MainActivity.vm
            }
    }

    /**
     * SecondActivityへ遷移する
     * @param v Layoutから直接呼ばれるためUNUSEDは無視する
     */
    fun next(@Suppress("UNUSED_PARAMETER") v: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }


    /**
     * SecondActivityへ遷移する
     * @param v Layoutから直接呼ばれるためUNUSEDは無視する
     */
    fun stopWatch(@Suppress("UNUSED_PARAMETER") v: View) {
        startActivity(Intent(this, StopWatchActivity::class.java))
    }
}

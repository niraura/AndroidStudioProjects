/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.brother.mfc.shenlong.R
import com.brother.mfc.shenlong.databinding.ActivitySecondBinding
import com.brother.mfc.shenlong.viewmodel.SecondViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * ２つ目の画面
 */
class SecondActivity : AppCompatActivity() {
    private val vm: SecondViewModel by viewModel()
    private lateinit var binding: ActivitySecondBinding

    /**
     *  レイアウトとバインディングを行う
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySecondBinding>(this, R.layout.activity_second).apply {
            lifecycleOwner = this@SecondActivity
            vm = this@SecondActivity.vm
        }
    }
}

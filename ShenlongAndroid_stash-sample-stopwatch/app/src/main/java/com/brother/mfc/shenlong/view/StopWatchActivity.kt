/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.brother.mfc.shenlong.R
import com.brother.mfc.shenlong.databinding.ActivityStopWatchBinding
import com.brother.mfc.shenlong.viewmodel.StopWatchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StopWatchActivity : AppCompatActivity() {
    private val vm: StopWatchViewModel by viewModel()
    private lateinit var binding: ActivityStopWatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityStopWatchBinding>(
            this,
            R.layout.activity_stop_watch
        ).apply {
            lifecycleOwner = this@StopWatchActivity
            vm = this@StopWatchActivity.vm
        }
    }
}

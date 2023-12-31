/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.brother.mfc.shenlong.model.StopWatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ストップウォッチのViewModel
 * @param stopWatch 使用するストップウォッチのモデル
 */
class StopWatchViewModel(val stopWatch: StopWatch) : BaseViewModel(),
    CoroutineScope by MainScope() {
    private var cleared = false

    /**
     * 現在の計測値
     */
    val timerCount = MutableLiveData<Long>()
    /**
     * 記録したラップ
     */
    val wraps = MutableLiveData<Array<Long>>()
    /**
     * 現在計測しているかどうか
     */
    val counting = MutableLiveData<Boolean>()
    /**
     * リセットできるか？
     */
    val canReset = MediatorLiveData<Boolean>().also { r ->
        r.addSource(counting) { r.value = !(counting.value ?: false) }
    }
    /**
     * ラップできるか？
     */
    val canWrap = MediatorLiveData<Boolean>().also { r ->
        r.addSource(counting) { r.value = counting.value }
    }

    init {
        counting.postValue(false)
        // 10ms間隔でtimerの表示を更新する
        launch {
            while (!cleared) {
                timerCount.postValue(stopWatch.current)
                delay(10)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // ViewModelが破棄されるので更新を停止
        cleared = true
    }


    /**
     * 計測の開始
     */
    fun start() {
        stopWatch.start()
        counting.postValue(true)
    }

    /**
     * 計測の終了
     */
    fun stop() {
        stopWatch.stop()
        counting.postValue(false)
    }

    /**
     * 計測結果のリセット
     */
    fun reset() {
        stopWatch.reset()
        wraps.postValue(stopWatch.wraps)
    }

    /**
     * ラップの追加
     */
    fun wrap() {
        stopWatch.wrap()
        wraps.postValue(stopWatch.wraps)
    }
}
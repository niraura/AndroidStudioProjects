/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model

import java.util.*

/**
 * 時間の計測を行うクラス
 */
class StopWatch {
    private var remain: Long = 0
    private var startTime: Date = Date()
    private var counting = false
    private val _wraps = ArrayList<Long>()

    /**
     * 記録済みのラップ
     */
    val wraps: Array<Long>
        get() = _wraps.toTypedArray()

    /**
     * 現在の計測値
     */
    val current: Long
        get() = if (counting) {
            Date().time - startTime.time + remain
        } else {
            remain
        }

    /**
     * 計測の開始
     */
    fun start() {
        if (counting)
            return

        counting = true
        startTime = Date()
    }

    /**
     * 計測の停止
     */
    fun stop() {
        if (!counting)
            return

        counting = false
        remain += Date().time - startTime.time
    }

    /**
     * ラップの追加
     */
    fun wrap() {
        if (!counting) {
            return
        }
        _wraps.add(current)
    }

    /**
     * 計測結果とラップのリセット
     */
    fun reset() {
        if (counting) {
            return
        }

        _wraps.clear()
        remain = 0
    }
}
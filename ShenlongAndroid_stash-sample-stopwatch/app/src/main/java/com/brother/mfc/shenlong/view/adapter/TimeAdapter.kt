/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.view.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TimeAdapter {

    /**
     * ミリ秒を文字列にフォーマットして設定する
     */
    @BindingAdapter("time")
    @JvmStatic
    fun TextView.setTime(time: Long?) {
        if (time == null) {
            this.text = ""
            return
        }


        this.text = format(time)
    }

    /**
     * ミリ秒の配列を文字にして設定する
     */
    @BindingAdapter("times")
    @JvmStatic
    fun TextView.setTimes(times: Array<Long>?) {
        if (times == null) {
            this.text = ""
            return
        }
        var txt = ""
        for (t in times) {
            txt += format(t) + "\n"
        }
        this.text = txt
    }

    /**
     * ミリ秒を文字列にフォーマットする
     * @param mill ミリ秒
     * @return 00:00:00.00
     */
    @JvmStatic
    fun format(mill: Long): String {
        val h = mill / 1000 / 60 / 60
        val m = (mill / 1000 / 60) % 60
        val s = (mill / 1000) % 60
        val ds = (mill / 10) % 100
        return "%02d:%02d:%02d.%02d".format(h, m, s, ds)
    }
}
/*
 * Copyright 2019 Brother Industries, Ltd.
 */

/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.log

import android.util.Log

class ConsoleLogger(private val tag: String, private val target:LogLevel) : Logger {
    override fun write(level: LogLevel, msg: String) {
        if (target.level > level.level) {
            return
        }
        when (level) {
            LogLevel.DEBUG -> Log.d(tag, msg)
            LogLevel.ERROR -> Log.e(tag, msg)
            LogLevel.NEVER -> Unit
        }
    }
}
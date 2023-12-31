/*
 * Copyright 2019 Brother Industries, Ltd.
 */

/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.log

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class FileLogger(
    private val file: File,
    private val target: LogLevel
) : Logger, CoroutineScope by MainScope() {
    private val lock = ReentrantLock()

    override fun write(level: LogLevel, msg: String) {
        if (target.level > level.level || level == LogLevel.NEVER) {
            return
        }

        launch(Dispatchers.IO) {
            lock.withLock {
                try {
                    val date = Date()
                    val format = SimpleDateFormat.getDateTimeInstance()
                    file.appendText("${format.format(date)} ${level.name} $msg\n")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
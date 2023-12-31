/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.bflog

import android.app.Application
import com.brother.bflog.BfLogFactory
import com.brother.bflog.BfLogger
import com.brother.mfc.shenlong.extension.resolve
import com.brother.mfc.shenlong.model.log.LogLevel
import com.brother.mfc.shenlong.model.log.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.context.GlobalContext
import java.io.File
import java.lang.Exception

class BfirstLogger(
    app: Application,
    dir: File,
    permission:Boolean,
    appId:String,
    appVersion:String,
    rotateLines:String = "10",
    remainingHours:String = "72"
    ) : AnalyticsLogger, CoroutineScope by MainScope() {
    companion object {
        @JvmStatic val DEVELOP = "https://dev-app.bolap.brother.co.jp/BrAnalytics/1.0/"
        @JvmStatic val RELEASE = "https://app.bolap.brother.com/BrAnalytics/1.0/"
    }
    private var log: BfLogger?

    init {
        val common = mapOf(
            "common.logVersion" to "1.00",
            "common.appId" to appId,
            "common.appVersion" to appVersion,
            "rotateLines" to rotateLines,
            "remainingHours" to remainingHours,
            "branalytics.baseUrl" to DEVELOP)
        val clientIdFile = File(dir, "client.txt")
        val logDir = File(dir, "log")

        try {
            log = BfLogFactory.createLogger(app, clientIdFile.absolutePath, logDir.absolutePath, permission, common)
        } catch (e: Exception) {
            GlobalContext.resolve<Logger>().write(LogLevel.DEBUG, e.toString())
            log = null
        }
    }

    override fun upload() {
        log?.forceUpload()
    }

    override fun put(data: Map<String, String>) {
        log?.out(data)
    }
}
/*
 * Copyright 2019 Brother Industries, Ltd.
 */
package com.brother.mfc.shenlong

import android.app.Application
import com.brother.mfc.shenlong.model.SampleModel
import com.brother.mfc.shenlong.model.StopWatch
import com.brother.mfc.shenlong.model.bflog.AnalyticsLogger
import com.brother.mfc.shenlong.model.bflog.BfirstLogger
import com.brother.mfc.shenlong.model.log.*
import com.brother.mfc.shenlong.viewmodel.MainViewModel
import com.brother.mfc.shenlong.viewmodel.SecondViewModel
import com.brother.mfc.shenlong.viewmodel.StopWatchViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

/**
 * アプリケーション
 * DIの設定も行う
 */
class App : Application() {
    /* 依存性の定義 */
    // Logger
    private lateinit var loggers: Module
    // Models
    private val models: Module = module {
        factory { SampleModel() }
        factory { StopWatch() }
    }

    // ViewModels
    private val viewModels: Module = module {
        viewModel { MainViewModel(get()) }
        viewModel { SecondViewModel() }
        viewModel { StopWatchViewModel(get()) }
    }

    /**
     * アプリケーションの初期化
     */
    override fun onCreate() {
        super.onCreate()
        // ログの設定
        loggers = getLoggerModule()

        // KOINの初期化
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(listOf(models, viewModels, loggers))
        }
    }

    private fun getLoggerModule(): Module {
        return module {
            single<Logger> {
                val configFile = File(getExternalFilesDir(null), "log.config")
                val config = if (configFile.exists()) Gson().fromJson(
                    configFile.readText(),
                    LogConfig::class.java
                ) else LogConfig()
                val level = LogLevel.valueOf(config.level)

                when (config.type) {
                    "File" -> FileLogger(File(getExternalFilesDir(null), "log.txt"), level)
                    else -> ConsoleLogger("Shenlong", level)
                }
            }
            single<AnalyticsLogger> {
                BfirstLogger(
                    this@App,
                    File(filesDir, "bflog"),
                    true,
                    "Shenlong",
                    packageManager.getPackageInfo(packageName, 0).versionName
                )
            }
        }
    }
}
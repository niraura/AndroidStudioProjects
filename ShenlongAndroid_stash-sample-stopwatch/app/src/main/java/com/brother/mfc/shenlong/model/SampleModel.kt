/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model

import com.brother.mfc.shenlong.extension.resolve
import com.brother.mfc.shenlong.model.log.LogLevel
import com.brother.mfc.shenlong.model.log.Logger
import org.koin.core.context.GlobalContext
import java.util.*

/**
 * サンプルモデル
 */
class SampleModel {
    private val logger = GlobalContext.resolve<Logger>()
    /**
     * 時刻を返す
     * @return 呼び出された時刻
     */
    fun getDate() : Date {
        val d = Date()
        logger.write(LogLevel.DEBUG, "SampleModel.getDate() = $d")
        return d
    }
}
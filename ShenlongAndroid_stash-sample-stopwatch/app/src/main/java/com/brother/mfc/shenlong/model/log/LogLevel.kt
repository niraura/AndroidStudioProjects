/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.log

enum class LogLevel(val level:Int) {
    DEBUG(100),
    ERROR(500),
    NEVER(1000)
}
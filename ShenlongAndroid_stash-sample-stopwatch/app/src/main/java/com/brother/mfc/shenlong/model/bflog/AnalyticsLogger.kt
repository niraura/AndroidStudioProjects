/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.bflog

interface AnalyticsLogger {
    fun put(data: Map<String, String>)
    fun upload()
}
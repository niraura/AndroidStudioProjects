/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.viewmodel

import androidx.lifecycle.ViewModel
import com.brother.mfc.shenlong.extension.resolve
import com.brother.mfc.shenlong.model.log.LogLevel
import com.brother.mfc.shenlong.model.log.Logger
import org.koin.core.context.GlobalContext

open class BaseViewModel : ViewModel() {
    init {
        GlobalContext.resolve<Logger>().write(LogLevel.DEBUG, "${this.javaClass.name}()")
    }

    override fun onCleared() {
        super.onCleared()
        GlobalContext.resolve<Logger>().write(LogLevel.DEBUG, "${this.javaClass.name}.onCleared()")
    }
}
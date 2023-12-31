/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.extension

import org.koin.core.context.GlobalContext

inline fun <reified T> GlobalContext.resolve() : T = GlobalContext.get().koin.get()
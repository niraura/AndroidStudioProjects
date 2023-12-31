/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.model.log

import java.io.Serializable

class LogConfig : Serializable {
    var type: String = "Console"
    var level: String = "NEVER"
}
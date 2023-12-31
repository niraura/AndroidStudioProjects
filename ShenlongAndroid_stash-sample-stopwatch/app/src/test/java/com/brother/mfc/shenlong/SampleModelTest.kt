package com.brother.mfc.shenlong

import com.brother.mfc.shenlong.extension.resolve
import com.brother.mfc.shenlong.model.SampleModel
import com.brother.mfc.shenlong.model.log.LogLevel
import com.brother.mfc.shenlong.model.log.Logger
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SampleModelTest : KoinTest {
    @Before
    fun before() {
        startKoin {
            modules(module {
                single<Logger> { MockLogger() }
            })
        }
    }

    @Test
    fun getDate() {
        val m = SampleModel()
        val d = m.getDate()
        val l = GlobalContext.resolve<Logger>() as MockLogger
        assertNotNull(d)
        assert(l.log.contains("SampleModel.getDate() = "))
    }

    inner class MockLogger : Logger {
        var log: String = ""
        override fun write(level: LogLevel, msg: String) {
            log = msg
        }

    }
}

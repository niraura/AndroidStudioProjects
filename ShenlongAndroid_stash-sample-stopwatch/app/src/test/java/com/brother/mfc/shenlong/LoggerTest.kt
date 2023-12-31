/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong

import com.brother.mfc.shenlong.model.log.LogConfig
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.rules.TemporaryFolder
import java.io.File

/**
 * FileLogger Test
 *
 */
class LoggerTest {
    private val tempDir = TemporaryFolder()

    @Before
    fun before() {
        tempDir.create()
    }

    private fun getFile(): File {
        val file = tempDir.newFile()
        if (file.exists())
            file.delete()
        return file
    }

    @Test
    fun fileLogger_never() {
        val file = getFile()
        val f = com.brother.mfc.shenlong.model.log.FileLogger(file, com.brother.mfc.shenlong.model.log.LogLevel.NEVER)

        f.write(com.brother.mfc.shenlong.model.log.LogLevel.ERROR, "test1")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.DEBUG, "test2")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.NEVER, "test3")
        Thread.sleep(100)

        assert(!file.exists())
    }


    @Test
    fun fileLogger_error() {
        val file = getFile()
        val f = com.brother.mfc.shenlong.model.log.FileLogger(file, com.brother.mfc.shenlong.model.log.LogLevel.ERROR)

        f.write(com.brother.mfc.shenlong.model.log.LogLevel.ERROR, "test1")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.DEBUG, "test2")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.NEVER, "test3")
        Thread.sleep(100)

        val log = file.readLines()
        assertEquals(1, log.count())
        assert(log[0].contains("test1"))
    }

    @Test
    fun fileLogger_debug() {
        val file = getFile()
        val f = com.brother.mfc.shenlong.model.log.FileLogger(file, com.brother.mfc.shenlong.model.log.LogLevel.DEBUG)

        f.write(com.brother.mfc.shenlong.model.log.LogLevel.ERROR, "test1")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.DEBUG, "test2")
        f.write(com.brother.mfc.shenlong.model.log.LogLevel.NEVER, "test3")
        Thread.sleep(100)

        val log = file.readLines()
        assertEquals(2, log.count())
        assert(log[0].contains("test1"))
        assert(log[1].contains("test2"))
    }

    @Test
    fun logger_config_default() {
        val config = LogConfig()

        assertEquals("Console", config.type)
        assertEquals("NEVER", config.level)
    }

    @Test
    fun logger_config_never() {
        val config = Gson().fromJson("{ \"type\":\"Console\", \"level\":\"NEVER\" }", LogConfig::class.java)

        assertEquals("Console", config.type)
        assertEquals("NEVER", config.level)
    }

    @Test
    fun logger_config_debug() {
        val config = Gson().fromJson("{ \"type\":\"File\", \"level\":\"DEBUG\" }", LogConfig::class.java)

        assertEquals("File", config.type)
        assertEquals("DEBUG", config.level)
    }

    @Test
    fun logger_config_error() {
        val config = Gson().fromJson("{ \"type\":\"Console\", \"level\":\"ERROR\" }", LogConfig::class.java)

        assertEquals("Console", config.type)
        assertEquals("ERROR", config.level)
    }
}
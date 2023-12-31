/*
 * Copyright 2019 Brother Industries, Ltd.
 */
package com.brother.mfc.shenlong

import com.brother.mfc.shenlong.model.StopWatch
import org.junit.Test

import org.junit.Assert.*
import org.junit.rules.Stopwatch

class StopWatchTest {
    @Test
    fun invalidOperation() {
        val w = StopWatch()

        w.start()
        w.start()

        Thread.sleep(100)

        w.stop()
        w.stop()

        assert(w.current in 100..150)
    }
    @Test
    fun getCurrent() {
        val w = StopWatch()
        w.start()
        Thread.sleep(500)
        assert(w.current >= 500)
        w.stop()
        Thread.sleep(100)
        assert(w.current in 500..599)
        w.start()
        Thread.sleep(100)
        w.stop()
        assert(w.current >= 600)
    }

    @Test
    fun start() {
        val w = StopWatch()
        w.start()
        Thread.sleep(500)
        assert(w.current >= 500)
    }

    @Test
    fun stop() {
        val w = StopWatch()
        w.start()
        Thread.sleep(500)
        w.stop()
        assert(w.current >= 500)
    }

    @Test
    fun wrap() {
        val w = StopWatch()

        w.wrap()
        // スタートしてないので0
        assertEquals(0, w.wraps.size)

        w.start()
        w.wrap()
        assertEquals(1, w.wraps.size)
        w.wrap()
        assertEquals(2, w.wraps.size)
    }

    @Test
    fun reset() {
        val w = StopWatch()
        w.start()
        Thread.sleep(100)
        w.wrap()
        Thread.sleep(100)
        w.wrap()
        Thread.sleep(100)
        w.wrap()
        Thread.sleep(100)

        w.reset() // Stopしていないので
        assertNotEquals(0, w.current)
        assertEquals(3, w.wraps.size)


        w.stop()
        w.reset()
        assertEquals(0, w.current)
        assertEquals(0, w.wraps.size)
    }
}
package com.app.juawcevada.revoluttest.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Gets the value of a LiveData safely.
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.observeTest() {
    val observer = Observer<T> { }
    this.observeForever(observer)
}
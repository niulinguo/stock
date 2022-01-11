package com.lingo.stock.libs.logger

import android.util.Log

class MyLoggerImpl(private val tag: String) : MyLogger {
    override fun debug(msg: String) {
        Log.d(tag, msg)
    }

    override fun info(msg: String) {
        Log.i(tag, msg)
    }

    override fun warn(msg: String) {
        Log.w(tag, msg)
    }

    override fun error(msg: String) {
        Log.e(tag, msg)
    }
}
package com.lingo.stock.libs.logger

interface MyLogger {
    fun debug(msg: String)
    fun info(msg: String)
    fun warn(msg: String)
    fun error(msg: String)
}
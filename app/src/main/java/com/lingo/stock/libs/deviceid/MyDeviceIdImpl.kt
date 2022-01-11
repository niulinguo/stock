package com.lingo.stock.libs.deviceid

import java.io.File
import java.util.*

class MyDeviceIdImpl(private val dir: File) : MyDeviceId {
    private val deviceId: String by lazy {
        val file = File(dir, "device_id.txt")
        if (file.exists()) {
            file.readText()
        } else {
            val uuid = UUID.randomUUID().toString()
            file.writeText(uuid)
            uuid
        }
    }

    override fun get(): String {
        return deviceId
    }
}
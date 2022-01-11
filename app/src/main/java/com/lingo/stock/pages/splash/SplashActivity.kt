package com.lingo.stock.pages.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lingo.stock.libs.deviceid.MyDeviceId
import com.lingo.stock.libs.logger.MyLogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var logger: MyLogger

    @Inject
    lateinit var deviceId: MyDeviceId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logger.info("onCreate ${deviceId.get()}")
    }
}
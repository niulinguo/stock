package com.lingo.stock.pages.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lingo.stock.libs.deviceid.MyDeviceId
import com.lingo.stock.libs.logger.MyLogger
import com.lingo.stock.pages.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var logger: MyLogger

    @Inject
    lateinit var deviceId: MyDeviceId

    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logger.info("onCreate ${deviceId.get()}")

        handler.postDelayed({
            navigateToLoginAndFinish()
        }, 3000)
    }

    private fun navigateToLoginAndFinish() {
        startActivity(Intent(this, LoginActivity::class.java))
        overridePendingTransition(0, android.R.anim.fade_out)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
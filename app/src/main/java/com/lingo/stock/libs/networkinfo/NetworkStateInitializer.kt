package com.lingo.stock.libs.networkinfo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.startup.Initializer

class NetworkStateInitializer : Initializer<Unit> {

    companion object {
        // 网络断开的时候，延时判断网络是否不可用
        // 有可能在切换网络
        private const val delayCheckTime = 3000L
    }

    private var networkValid: MutableLiveData<Boolean> = MutableLiveData()

    private val handler: Handler = Handler(Looper.getMainLooper())

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    private lateinit var connectivityManager: ConnectivityManager

    private val availableNetworks: MutableSet<Network> = mutableSetOf()

    private val networkCallback: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                availableNetworks.add(network)
                handler.removeCallbacksAndMessages(null)
                updateNetworkValid()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                availableNetworks.remove(network)
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed(checkNetworkValidRunnable, delayCheckTime)
            }
        }
    }

    private val checkNetworkValidRunnable: Runnable = Runnable {
        updateNetworkValid()
    }

    private fun updateNetworkValid() {
        val valid = availableNetworks.isNotEmpty()
        if (networkValid.value != valid) {
            networkValid.postValue(valid)
        }
    }

    override fun create(context: Context) {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val valid = connectivityManager.activeNetwork != null
            if (networkValid.value != valid) {
                networkValid.value = valid
            }
        } else {
            if (networkValid.value != true) {
                networkValid.value = true
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        networkValid.observeForever {
            if (it == null) {
                return@observeForever
            }
            Log.e("网络状态", if (it) "可用" else "不可用")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
package com.navega.entre.moviapp.core

import android.util.Log
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.math.log

object internetCheck {
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val sockeAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockeAddress, 2000)
            true



        } catch (e: Exception) {
            false





        }
    }
}
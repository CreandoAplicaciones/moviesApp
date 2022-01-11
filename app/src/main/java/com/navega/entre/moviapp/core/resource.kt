package com.navega.entre.moviapp.core

import android.content.res.Resources
import java.lang.Exception

sealed class resource<out T> {
    //3 states
    //loanding
    //with data
    //no data

    //app loanding
    class Loanding<out T>: resource<T>()
    // app collect data
    data class Success<out T>(val data:T):resource<T>()
    //no data found
    data class Failure(val exception: Exception):resource<Nothing>()


}
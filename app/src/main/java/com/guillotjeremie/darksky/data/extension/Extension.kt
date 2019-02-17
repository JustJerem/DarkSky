package com.guillotjeremie.darksky.data.extension

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*


fun Context.isOnline(): Boolean {
    val mConMgr = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return (mConMgr.activeNetworkInfo != null && mConMgr.activeNetworkInfo.isConnected)
}

fun Long.toDayLetterDate(): String = SimpleDateFormat("EEEE dd/MM", Locale.US).format(Date(this * 1000))

fun Long.toCompleteLetterDate(): String = SimpleDateFormat("EEEE, hh:mm aaa", Locale.US).format(Date(this * 1000))


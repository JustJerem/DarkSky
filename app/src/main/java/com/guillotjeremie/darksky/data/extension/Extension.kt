package com.guillotjeremie.darksky.data.extension

import android.content.Context
import android.net.ConnectivityManager


fun Context.isOnline(): Boolean {
    val mConMgr = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return (mConMgr.activeNetworkInfo != null && mConMgr.activeNetworkInfo.isConnected)
}
package com.example.callbackpractice.utils

import android.util.Log

object PrintLog {

    private val TAG = "PrintLog"

    fun printI(tag: String, message: String) {
        Log.d(TAG, "$tag: $message")
    }

    fun printE(tag: String, message: String) {
        Log.e(TAG, "$tag: $message")
    }
}
package com.example.callbackpractice.ui.theme

import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager

import com.example.callbackpractice.utils.PrintLog


object AppVersionName {

    private const val TAG = "AppVersionName"
   val ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";
   val intent = IntentFilter()

    private val PACKAGE_NAME = "com.sec.android.app.shealth"
   // private const val PACKAGE_NAME = "com.kyant.vanilla"
    fun findPackage(context: Context): String? {
        return try {
            context.packageManager.getPackageInfo(
                PACKAGE_NAME,
                PackageManager.GET_META_DATA
            ).versionName
        } catch (e: Exception) {
            PrintLog.printE(TAG, "error occure $e")
            e.printStackTrace()
            null
        }
    }
}
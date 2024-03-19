package com.example.callbackpractice.fragment

import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.callbackpractice.R
import com.example.callbackpractice.databinding.SamsungHealthFragmentBinding
import com.example.callbackpractice.ui.theme.AppVersionName
import com.example.callbackpractice.utils.PrintLog
import com.example.callbackpractice.viewmodel.MyViewModel

class SamsungHealthFragment : BaseFragment() {
    private val TAG = "SamsungHealthFragment"
    private val PACKAGE_NAME = "com.sec.android.app.shealth"
    private val MINIMUM_SUPPORT = "6.27"
    private lateinit var viewMode: MyViewModel
    private var _binding: SamsungHealthFragmentBinding? = null

    private val binding get() = _binding!!
    override fun create() {
         PrintLog.e(TAG,"create : invoke")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val appInstallStatus = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            handleAppStatus()
        }
    }

    private fun handleAppStatus() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SamsungHealthFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AppVersionName.findPackage(context)?.let { version ->
            PrintLog.d(TAG, "App found: version $version")

            if (version < MINIMUM_SUPPORT) {
                binding.tvText.text = getString(R.string.update)
                binding.btn.text = getString(R.string.update)
            } else {
                binding.tvText.text = getString(R.string.app_install)
            }
        }

        binding.btn.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$PACKAGE_NAME")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                PrintLog.e(TAG, "error occur : ${e.message}")
                e.printStackTrace()
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_MY_PACKAGE_REPLACED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_UNINSTALL_PACKAGE)

    }

    override fun onStart() {
        super.onStart()

        registrationBroadcast()
    }

    private fun registrationBroadcast() {

        context.registerReceiver(appInstallStatus, IntentFilter().also {
            it.priority = 999
            it.addAction(Intent.ACTION_MY_PACKAGE_REPLACED)
            it.addAction(Intent.ACTION_PACKAGE_ADDED)
            it.addAction(Intent.ACTION_UNINSTALL_PACKAGE)
            it.addDataSchemeSpecificPart("package",1)
        }, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onStop() {
        super.onStop()
        context.unregisterReceiver(appInstallStatus)
    }

}
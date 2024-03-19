package com.example.callbackpractice.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.callbackpractice.R
import com.example.callbackpractice.fragment.SamsungHealthFragment

class MainActivity : ComponentActivity() {


    private val TAG = "MainActivity"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, SamsungHealthFragment())?.commit()

    }
}
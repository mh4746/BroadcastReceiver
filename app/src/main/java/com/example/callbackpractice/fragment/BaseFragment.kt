package com.example.callbackpractice.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.callbackpractice.R


abstract class BaseFragment : Fragment() {

    abstract fun create()
    open fun log() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    companion object {
        private const val TAG = "BaseFragment"

    }
}
package com.jeepchief.newlotterycheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jeepchief.newlotterycheck.util.Log

open class BaseActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate()")

//        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.e("onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy()")
    }
}
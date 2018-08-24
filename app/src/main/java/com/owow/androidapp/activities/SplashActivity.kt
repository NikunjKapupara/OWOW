package com.owow.androidapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.owow.androidapp.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        navigateToLoginScreen()
    }

    /**
     * do Navigation after 2 seconds to Login Activity
     */
    private fun navigateToLoginScreen() {
        try {
            val timerThread = object : Thread() {
                override fun run() {
                    try {
                        Thread.sleep(2000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    } finally {
                        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            timerThread.start()
        }
        catch (e:Exception){
            e.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}

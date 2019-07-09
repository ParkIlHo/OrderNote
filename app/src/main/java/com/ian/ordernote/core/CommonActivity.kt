package com.ian.ordernote.core

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ian.ordernote.MainActivity
import com.ian.ordernote.PasswordActivity
import com.ian.ordernote.SplashActivity

open class CommonActivity: AppCompatActivity() {

    protected var isLock = false
    protected val lockTime = 1000*60

    protected val INTENT_KEY_LOCK = "intent_key_lock"

    override fun onPause() {
        super.onPause()
        Log.e("151515", "onPause - " + this.localClassName)
        OrderNotePrefs(applicationContext).pauseTime = System.currentTimeMillis()
    }

    override fun onStop() {
        super.onStop()
        Log.e("151515", "onStop - " + this.localClassName)
    }

    override fun onResume() {
        super.onResume()
        Log.e("151515", "onResume - " + this.localClassName)

        var current = System.currentTimeMillis()
        var pauseTime = OrderNotePrefs(applicationContext).pauseTime

        if(this is SplashActivity || this is PasswordActivity) {
            isLock = false
        } else {
            if(pauseTime <= 0) {
                isLock = false
            } else if((current - pauseTime) > lockTime) {
                isLock = true
            } else {
                isLock = false
            }
        }

        when(isLock) {
            true -> {
                var intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra(INTENT_KEY_LOCK, true)
                goActivity(intent, false)
            }
        }
    }

    override fun onBackPressed() {
        if(this is PasswordActivity && isIntentLock) {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }

    protected fun goActivity(intent: Intent, isFinish: Boolean) {
        startActivity(intent)
        if(isFinish) {
            finish()
        }
    }


}
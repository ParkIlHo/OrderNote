package com.ian.ordernote

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val REQUEST_PERMISSIONS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android M 이상일 경우 Permission check
            var perReEx = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            var perWrEx = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if (perReEx == PackageManager.PERMISSION_DENIED || perWrEx == PackageManager.PERMISSION_DENIED) { // 권한 요청 실행
                initPermission()
            } else {
                init()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.size != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                init()
            } else {
                finish()
            }
        }
    }

    fun init() {
        val r = Runnable {
//            val intent = Intent(this, MainActivity::class.java)
            val intent = Intent(this, PasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(r, 2000)
    }

    fun initPermission() {
        val permissions =
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, REQUEST_PERMISSIONS)
        }
    }
}
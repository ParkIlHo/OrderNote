package com.ian.ordernote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)

        init()
    }

    fun init() {
        setting_toolbar.setTitle(R.string.setting)

        setSupportActionBar(setting_toolbar)

        setting_toolbar.setNavigationIcon(R.drawable.back_icon)
        setting_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
}
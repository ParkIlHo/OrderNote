package com.ian.ordernote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ian.ordernote.core.CommonActivity
import com.ian.ordernote.dialog.PasswordChangeDialog
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: CommonActivity(), View.OnClickListener{

    lateinit var passwordDialog: PasswordChangeDialog

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.setting_password_change_btn -> {
                if(passwordDialog != null) {
                    if(!passwordDialog.isShowing) {
                        passwordDialog.show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)

        init()
    }

    fun init() {
        passwordDialog = PasswordChangeDialog(this)

        setting_toolbar.setTitle(R.string.setting)

        setSupportActionBar(setting_toolbar)

        setting_toolbar.setNavigationIcon(R.drawable.back_icon)
        setting_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        setting_password_change_btn.setOnClickListener(this)
    }
}
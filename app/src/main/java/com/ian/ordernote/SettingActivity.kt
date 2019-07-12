package com.ian.ordernote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.ian.ordernote.core.CommonActivity
import com.ian.ordernote.core.OrderNotePrefs
import com.ian.ordernote.dialog.PasswordChangeDialog
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: CommonActivity(), View.OnClickListener, TextWatcher{

    lateinit var passwordDialog: PasswordChangeDialog

    val DEFAULT_LOCK_TIME = 30

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

        setting_security_time_edit.addTextChangedListener(this)

        setting_security_switch.isChecked = prefs?.isScreenLock!!
        when(setting_security_switch.isChecked) {
            true -> {
                setScreenLock(true)
                setting_security_time_edit.setText(prefs?.screenLockTime.toString())
            }
            false -> {
                setScreenLock(false)
            }
        }
        setting_security_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked) {
                true -> {
                    setScreenLock(true)
                    setting_security_time_edit.setText(DEFAULT_LOCK_TIME.toString())
                }
                false -> {
                    setScreenLock(false)
                }
            }
        }
    }

    fun setScreenLock(isShow: Boolean) {
        if(isShow) {
            setting_security_second_layout.visibility = View.VISIBLE
            prefs?.isScreenLock = true
            setting_security_switch.setText("On")
        } else {
            setting_security_second_layout.visibility = View.GONE
            prefs?.isScreenLock = false
            setting_security_switch.setText("Off")
        }
    }

    /**
     * onClick
     */
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

    /**
     * TextWahtcher
     */
    override fun afterTextChanged(str: Editable?) {
        var time = 0;

        if(!TextUtils.isEmpty(str)) {
            time = str.toString().toInt()
        }
        prefs?.screenLockTime = time
    }

    override fun beforeTextChanged(str: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(str: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
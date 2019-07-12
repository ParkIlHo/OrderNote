package com.ian.ordernote.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.OrderListActivity
import com.ian.ordernote.R
import com.ian.ordernote.core.CommonAlertDialog
import com.ian.ordernote.core.OrderNotePrefs
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB
import org.w3c.dom.Text
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PasswordChangeDialog(context: Context?) : CommonAlertDialog(context), View.OnClickListener {

    lateinit var mContext: Context
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialogView: View

    lateinit var mPassword: EditText
    lateinit var mNewPassword: EditText
    lateinit var mConfirmPassword: EditText

    init {

        mContext = context!!
        mDialogView = layoutInflater.inflate(R.layout.dialog_password_change, null)
        setView(mDialogView)

        mPassword = mDialogView.findViewById<EditText>(R.id.dailog_password_change_current_password)
        mNewPassword = mDialogView.findViewById<EditText>(R.id.dailog_password_change_new_password)
        mConfirmPassword = mDialogView.findViewById<EditText>(R.id.dailog_password_change_new_password_confirm)

        mDialogView.findViewById<Button>(R.id.dialog_password_cancel).setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_password_change).setOnClickListener(this)
    }

    override fun dismiss() {
        super.dismiss()
        Log.e("151515", "OrderDialog dismiss")
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.dialog_password_cancel -> { // cancel
                dismiss()
            }

            R.id.dialog_password_change -> { // change
                var pefs = OrderNotePrefs(mContext)
                var savePassword = pefs.password
                var password = mPassword.text.toString()
                var newPassword = mNewPassword.text.toString()
                var confirmPassword = mConfirmPassword.text.toString()

                val builder2 = AlertDialog.Builder(mContext)

                if(TextUtils.isEmpty(password)) {
                    builder2
                        .setMessage(R.string.setting_input_password)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                    return
                } else if(TextUtils.isEmpty(newPassword)) {
                    builder2
                        .setMessage(R.string.setting_input_new_password)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                    return
                } else if(TextUtils.isEmpty(confirmPassword)) {
                    builder2
                        .setMessage(R.string.setting_input_confirm_password)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                    return
                }

                if(savePassword.equals(password)) {
                    if(newPassword.equals(confirmPassword)) {
                        pefs.password = newPassword
                        builder2
                            .setMessage(R.string.setting_complete_password)
                            .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
                                dismiss()
                            }
                            .show()
                        return
                    } else {
                        builder2
                            .setMessage(R.string.setting_error_new_password)
                            .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                            }
                            .show()
                        return
                    }
                } else {
                    builder2
                        .setMessage(R.string.setting_error_password)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                    return
                }

            }

        }
    }


    override fun show() {
        super.show()
        init()
    }

    fun init() {
        mPassword.setText("")
        mNewPassword.setText("")
        mConfirmPassword.setText("")
    }
}
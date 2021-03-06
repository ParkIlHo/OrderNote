package com.ian.ordernote.core

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils

open class CommonAlertDialog(context: Context?) : AlertDialog(context) {
    fun showAlertDialog(context: Context, msg: String, confirnBtn: String?, cancelBtn: String?) {
        val builder2 = AlertDialog.Builder(context)

        builder2.setMessage(msg)

        if(!TextUtils.isEmpty(confirnBtn)) {
            builder2.setPositiveButton(confirnBtn) { dialogInterface: DialogInterface?, i: Int ->

            }
        }

        if(!TextUtils.isEmpty(cancelBtn)) {
            builder2.setNegativeButton(cancelBtn) { dialogInterface: DialogInterface?, i: Int ->

            }
        }

        builder2.show()
    }

    fun showAlertDialog(context: Context, msg: String, confirnBtn: String?, cancelBtn: String?, confirmInterface: DialogInterface.OnClickListener, cancelInterface: DialogInterface.OnClickListener?) {
        val builder2 = AlertDialog.Builder(context)

        builder2.setMessage(msg)

        if(!TextUtils.isEmpty(confirnBtn)) {
            if(confirmInterface != null) {
                builder2.setPositiveButton(confirnBtn, confirmInterface)
            } else {
                builder2.setPositiveButton(confirnBtn) { dialogInterface: DialogInterface?, i: Int ->

                }
            }
        }

        if(!TextUtils.isEmpty(cancelBtn)) {
            if(cancelInterface != null) {

            } else {
            builder2.setNegativeButton(cancelBtn) { dialogInterface: DialogInterface?, i: Int ->

            }}
        }

        builder2.show()
    }
}
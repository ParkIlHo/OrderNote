package com.ian.ordernote.dialog

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import com.ian.ordernote.R
import com.ian.ordernote.data.OrderInfo

class OrderDialog(context: Context?) : AlertDialog(context), View.OnClickListener {

    lateinit var mContext: Context
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialogView: View

    init {
        mContext = context!!
        mDialogView = layoutInflater.inflate(R.layout.dialog_add_order, null)
        setView(mDialogView)

        mDialogView.findViewById<Button>(R.id.dialog_add_order_cancel_btn).setOnClickListener(this)
    }

    override fun dismiss() {
        super.dismiss()
        Log.e("151515", "OrderDialog dismiss")
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.dialog_add_order_cancel_btn -> { // Add cancel
                if(isShowing) {
                    dismiss()
                }
            }

            R.id.dialog_add_order_save_btn -> { // Add 저장

            }

            R.id.dialog_add_order_change_btn -> { // 수정 버튼

            }

            R.id.dialog_add_order_confirm_btn -> { // 확인 버튼

            }
        }
    }

    fun setOrderInfo(orderInfo: OrderInfo) {

    }
}
package com.ian.ordernote.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.OrderListActivity
import com.ian.ordernote.R
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB
import com.ian.ordernote.dialog.OrderDialog

class OrderListAdapter(val context: Context, var orderList: ArrayList<OrderInfo>, val listener: CustomerListActivity.CustomerChangeListener) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return orderList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return orderList.size
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_list, null)

        val name = view.findViewById<TextView>(R.id.item_order_list_name)
        val mobile = view.findViewById<TextView>(R.id.item_order_list_mobile)
        val productName = view.findViewById<TextView>(R.id.item_order_list_product_name)
        val orderDate = view.findViewById<TextView>(R.id.item_order_list_order_date)
        val release = view.findViewById<TextView>(R.id.item_order_list_release)
        val delBtn = view.findViewById<Button>(R.id.item_order_list_delete)

        var orderInfo = orderList[position]

        name.text = orderInfo.name
        mobile.text = orderInfo.tel
        productName.text = orderInfo.productName
        orderDate.text = orderInfo.orderDate
        release.text = orderInfo.releaseYN

        delBtn.setOnClickListener { view: View ->
            val builder2 = AlertDialog.Builder(context)

            builder2
                .setMessage(context.getString(R.string.delete_order, orderInfo.name, orderInfo.tel, orderInfo.productName))
                .setPositiveButton(R.string.delete) { dialogInterface: DialogInterface?, i: Int ->
                    //delete
                    DB(context).getInstance(context).delOrder(orderInfo)
                    listener.onDelete()
                }
                .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                    //cancel
                }
                .show()
        }

        view.setOnClickListener { view: View ->

            var orderDialog = OrderDialog(context, listener)

            orderDialog.isAdd = false
            orderDialog.setOrderInfo(orderInfo)
            orderDialog.show()
        }

//        view.setOnClickListener { view : View ->
//
//            var tempCustomerInfo: CustomerInfo = customerInfo
//
//            val builder = AlertDialog.Builder(context)
//            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_customer, null)
//
//            val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_add_customer_title)
//
//            val dialogBtnLayout = dialogView.findViewById<LinearLayout>(R.id.dailog_add_customer_add_btn_layout)
//            val dialogDetailLayout = dialogView.findViewById<LinearLayout>(R.id.dailog_add_customer_detail_btn_layout)
//
//            val dialogSaveBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_save_btn)
//            val dialogCancelBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_cancel_btn)
//
//            val dialogChangeBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_change_btn)
//            val dialogConfirmBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_confirm_btn)
//
//            val nameEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_name)
//            val nameText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_name_text)
//            val mobileEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_mobile)
//            val mobileText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_mobile_text)
//            val telEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_tel)
//            val telText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_tel_text)
//            val emailEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_email)
//            val emailText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_email_text)
//            val otherEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_other)
//            val otherText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_other_text)
//
//            builder.setView(dialogView)
//                .setCancelable(false)
//
//            fun setText() {
//                nameEdit.setText(tempCustomerInfo.name)
//                mobileEdit.setText(tempCustomerInfo.mobile)
//                telEdit.setText(tempCustomerInfo.tel)
//                emailEdit.setText(tempCustomerInfo.email)
//                otherEdit.setText(tempCustomerInfo.other)
//
//                nameText.setText(tempCustomerInfo.name)
//                mobileText.setText(tempCustomerInfo.mobile)
//                telText.setText(tempCustomerInfo.tel)
//                emailText.setText(tempCustomerInfo.email)
//                otherText.setText(tempCustomerInfo.other)
//            }
//
//            fun setCustomerInfo() {
//                tempCustomerInfo.name = nameEdit.text.toString()
//                tempCustomerInfo.mobile = mobileEdit.text.toString()
//                tempCustomerInfo.tel = telEdit.text.toString()
//                tempCustomerInfo.email = emailEdit.text.toString()
//                tempCustomerInfo.other = otherEdit.text.toString()
//            }
//
//            fun setLayout(isDetail: Boolean) {
//                when(isDetail) {
//                    true -> {
//                        dialogTitle.setText(context.getString(R.string.detail_custmer))
//                        dialogDetailLayout.visibility = View.VISIBLE
//                        dialogBtnLayout.visibility = View.GONE
//
//                        nameEdit.visibility = View.GONE
//                        mobileEdit.visibility = View.GONE
//                        telEdit.visibility = View.GONE
//                        emailEdit.visibility = View.GONE
//                        otherEdit.visibility = View.GONE
//
//                        nameText.visibility = View.VISIBLE
//                        mobileText.visibility = View.VISIBLE
//                        telText.visibility = View.VISIBLE
//                        emailText.visibility = View.VISIBLE
//                        otherText.visibility = View.VISIBLE
//                    }
//                    false -> {
//                        dialogTitle.setText(context.getString(R.string.change_custmer))
//                        dialogDetailLayout.visibility = View.GONE
//                        dialogBtnLayout.visibility = View.VISIBLE
//
//                        nameEdit.visibility = View.VISIBLE
//                        mobileEdit.visibility = View.VISIBLE
//                        telEdit.visibility = View.VISIBLE
//                        emailEdit.visibility = View.VISIBLE
//                        otherEdit.visibility = View.VISIBLE
//
//                        nameText.visibility = View.GONE
//                        mobileText.visibility = View.GONE
//                        telText.visibility = View.GONE
//                        emailText.visibility = View.GONE
//                        otherText.visibility = View.GONE
//                    }
//                }
//            }
//
//            setLayout(true)
//            setText()
//
//            val dialog = builder.show()
//
//            dialogConfirmBtn.setOnClickListener { view : View -> // 확인 버튼 클릭시
//                if(dialog.isShowing) {
//                    dialog.dismiss()
//                }
//            }
//
//            dialogChangeBtn.setOnClickListener { view : View -> // 수정 버튼 클릭시
//                val builder2 = AlertDialog.Builder(context)
//                builder2
//                    .setMessage(R.string.change_customer_msg)
//                    .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
//                        setLayout(false)
//                    }
//                    .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
//                    }.show()
//            }
//
//
//            dialogSaveBtn.setOnClickListener { view : View ->
//                val builder2 = AlertDialog.Builder(context)
//
//                //필수 입력사항 체크
//                if(!TextUtils.isEmpty(nameEdit.text) && !TextUtils.isEmpty(mobileEdit.text)) { // 필수 입력사항 완료
//                    builder2
//                        .setMessage(context.getString(R.string.save_change_customer, nameEdit.text, mobileEdit.text))
//                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
//
//                            setCustomerInfo()
//
//                            var result = DB(context).getInstance(context).setCustomer(tempCustomerInfo)
//
//                            Log.e("DB setCustomer", "result = ${result}")
//
//                            // db 저장
//                            if(result > 0) {
//                                setText()
//                                setLayout(true)
//                                listener.onAdd()
//                            } else {
//                                tempCustomerInfo = customerInfo
//                                setText()
//                            }
//                        }
//                        .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
//
//                        }
//                        .show()
//                } else { // 필수 입력사항 미완료
//                    builder2
//                        .setMessage(R.string.customer_check_msg)
//                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
//
//                        }
//                        .show()
//                }
//
//            }
//
//            dialogCancelBtn.setOnClickListener { view : View ->
//                val builder2 = AlertDialog.Builder(context)
//                builder2
//                    .setMessage(R.string.cancel_change_customer)
//                    .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
//                        setLayout(true)
//                        setText()
//                    }
//                    .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
//                    }.show()
//
//            }
//        }

        return view
    }
}
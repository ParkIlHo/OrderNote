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
import com.ian.ordernote.db.DB

class CustomerSelectListAdapter(val context: Context, var customerList: ArrayList<CustomerInfo>, val listener: OrderListActivity.OrderInfoListener) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return customerList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return customerList.size
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_customer_select_list, null)

        val name = view.findViewById<TextView>(R.id.item_customer_select_list_name)
        val mobile = view.findViewById<TextView>(R.id.item_customer_select_list_mobile)
        val selectBtn = view.findViewById<Button>(R.id.item_customer_select_list_select)

        var customerInfo = customerList[position]

        name.text = customerInfo.name
        mobile.text = customerInfo.tel

        selectBtn.setOnClickListener { view: View ->
            val builder2 = AlertDialog.Builder(context)

            builder2
                .setMessage(context.getString(R.string.select_customer_msg, customerInfo.name, customerInfo.tel))
                .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
                    listener.selectCustomer(customerInfo)
                }
                .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                    //cancel
                }
                .show()
        }

        view.setOnClickListener { view : View ->

            var tempCustomerInfo: CustomerInfo = customerInfo

            val builder = AlertDialog.Builder(context)
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_customer, null)

            val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_add_customer_title)

            val dialogBtnLayout = dialogView.findViewById<LinearLayout>(R.id.dailog_add_customer_add_btn_layout)
            val dialogDetailLayout = dialogView.findViewById<LinearLayout>(R.id.dailog_add_customer_detail_btn_layout)

            val dialogSaveBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_save_btn)
            val dialogCancelBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_cancel_btn)

            val dialogChangeBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_change_btn)
            val dialogConfirmBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_confirm_btn)

            val nameEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_name)
            val nameText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_name_text)
            val telEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_tel)
            val telText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_tel_text)
            val emailEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_email)
            val emailText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_email_text)
            val otherEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_other)
            val otherText = dialogView.findViewById<TextView>(R.id.dialog_add_customer_other_text)

            builder.setView(dialogView)
                .setCancelable(false)

            fun setText() {
                nameEdit.setText(tempCustomerInfo.name)
                telEdit.setText(tempCustomerInfo.tel)
                emailEdit.setText(tempCustomerInfo.email)
                otherEdit.setText(tempCustomerInfo.other)

                nameText.setText(tempCustomerInfo.name)
                telText.setText(tempCustomerInfo.tel)
                emailText.setText(tempCustomerInfo.email)
                otherText.setText(tempCustomerInfo.other)
            }

            fun setCustomerInfo() {
                tempCustomerInfo.name = nameEdit.text.toString()
                tempCustomerInfo.tel = telEdit.text.toString()
                tempCustomerInfo.email = emailEdit.text.toString()
                tempCustomerInfo.other = otherEdit.text.toString()
            }

            fun setLayout(isDetail: Boolean) {
                when(isDetail) {
                    true -> {
                        dialogTitle.setText(context.getString(R.string.detail_custmer))
                        dialogDetailLayout.visibility = View.VISIBLE
                        dialogBtnLayout.visibility = View.GONE

                        nameEdit.visibility = View.GONE
                        telEdit.visibility = View.GONE
                        emailEdit.visibility = View.GONE
                        otherEdit.visibility = View.GONE

                        nameText.visibility = View.VISIBLE
                        telText.visibility = View.VISIBLE
                        emailText.visibility = View.VISIBLE
                        otherText.visibility = View.VISIBLE
                    }
                    false -> {
                        dialogTitle.setText(context.getString(R.string.change_custmer))
                        dialogDetailLayout.visibility = View.GONE
                        dialogBtnLayout.visibility = View.VISIBLE

                        nameEdit.visibility = View.VISIBLE
                        telEdit.visibility = View.VISIBLE
                        emailEdit.visibility = View.VISIBLE
                        otherEdit.visibility = View.VISIBLE

                        nameText.visibility = View.GONE
                        telText.visibility = View.GONE
                        emailText.visibility = View.GONE
                        otherText.visibility = View.GONE
                    }
                }
            }

            setLayout(true)
            setText()

            val dialog = builder.show()

            dialogConfirmBtn.setOnClickListener { view : View -> // 확인 버튼 클릭시
                if(dialog.isShowing) {
                    dialog.dismiss()
                }
            }

            dialogChangeBtn.setOnClickListener { view : View -> // 수정 버튼 클릭시
                val builder2 = AlertDialog.Builder(context)
                builder2
                    .setMessage(R.string.change_customer_msg)
                    .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
                        setLayout(false)
                    }
                    .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                    }.show()
            }


            dialogSaveBtn.setOnClickListener { view : View ->
                val builder2 = AlertDialog.Builder(context)

                //필수 입력사항 체크
                if(!TextUtils.isEmpty(nameEdit.text) && !TextUtils.isEmpty(telEdit.text)) { // 필수 입력사항 완료
                    builder2
                        .setMessage(context.getString(R.string.save_change_customer, nameEdit.text, telEdit.text))
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                            setCustomerInfo()

                            var result = DB(context).getInstance(context).setCustomer(tempCustomerInfo)

                            Log.e("DB setCustomer", "result = ${result}")

                            // db 저장
                            if(result > 0) {
                                setText()
                                setLayout(true)
                                listener.onAdd()
                            } else {
                                tempCustomerInfo = customerInfo
                                setText()
                            }
                        }
                        .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                } else { // 필수 입력사항 미완료
                    builder2
                        .setMessage(R.string.customer_check_msg)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                        }
                        .show()
                }

            }

            dialogCancelBtn.setOnClickListener { view : View ->
                val builder2 = AlertDialog.Builder(context)
                builder2
                    .setMessage(R.string.cancel_change_customer)
                    .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
                        setLayout(true)
                        setText()
                    }
                    .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                    }.show()

            }
        }

        return view
    }
}
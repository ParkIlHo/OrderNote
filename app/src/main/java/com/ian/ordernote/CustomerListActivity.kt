package com.ian.ordernote

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.db.DB
import com.ian.ordernote.view.CustomerListAdapter
import kotlinx.android.synthetic.main.activity_customer_list.*
import java.util.*

class CustomerListActivity: AppCompatActivity() {

    var mDb : DB? = null
    lateinit var mAdapter: CustomerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_list)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.customer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("StringFormatMatches")
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_customer_add -> {
                Toast.makeText(this, "주문자 추가 화면 이동", Toast.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.dialog_add_customer, null)

                val dialogSaveBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_save_btn)
                val dialogCancelBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_cancel_btn)

                val nameEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_name)
                val mobileEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_mobile)
                val telEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_tel)
                val emailEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_email)
                val otherEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_other)

                builder.setView(dialogView)
                    .setCancelable(false)


                val dialog = builder.show()

                dialogSaveBtn.setOnClickListener { view : View ->
                    val builder2 = AlertDialog.Builder(this)

                    //필수 입력사항 체크
                    if(!TextUtils.isEmpty(nameEdit.text) && !TextUtils.isEmpty(mobileEdit.text)) { // 필수 입력사항 완료
                        builder2
                            .setMessage(getString(R.string.save_add_customer, nameEdit.text, mobileEdit.text))
                            .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->

                                var customer = CustomerInfo()
                                customer.name = nameEdit.text.toString()
                                customer.mobile = mobileEdit.text.toString()
                                customer.tel = telEdit.text.toString()
                                customer.email = emailEdit.text.toString()
                                customer.other = otherEdit.text.toString()

                                var result = mDb?.setCustomer(customer)

                                Log.e("DB setCustomer", "result = ${result}")

                                // db 저장
                                dialog.dismiss()

                                initCustomerList()
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
                    val builder2 = AlertDialog.Builder(this)
                    builder2
                        .setMessage(R.string.cancel_add_customer)
                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
                            if(dialog.isShowing) {
                                dialog.dismiss()
                            }
                        }
                        .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                        }.show()

                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        mDb = DB(applicationContext).getInstance(applicationContext)

        customer_list_toolbar.setTitle(R.string.title_custmer_list)

        setSupportActionBar(customer_list_toolbar)

        customer_list_toolbar.setNavigationIcon(R.drawable.back_icon)
        customer_list_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        initCustomerList()
    }

    fun initCustomerList() {
        var list = mDb?.getCustomer()

        mAdapter = CustomerListAdapter(this, list!!)

        customer_list.adapter = mAdapter

        if(mAdapter.count > 0) {
            customer_list_no.visibility = View.GONE
            customer_list_layout.visibility = View.VISIBLE
        } else {
            customer_list_no.visibility = View.VISIBLE
            customer_list_layout.visibility = View.GONE
        }
    }
}
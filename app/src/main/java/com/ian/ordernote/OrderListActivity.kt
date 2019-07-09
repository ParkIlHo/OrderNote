package com.ian.ordernote

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ian.ordernote.core.CommonActivity
import com.ian.ordernote.db.DB
import com.ian.ordernote.dialog.OrderDialog
import com.ian.ordernote.view.CustomerListAdapter
import com.ian.ordernote.view.OrderListAdapter
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity: CommonActivity() {

    var mDb : DB? = null
    lateinit var mAdapter: OrderListAdapter

    val listener: CustomerListActivity.CustomerChangeListener? = object : CustomerListActivity.CustomerChangeListener {
        override fun onDelete() {
            initOrderList()
        }

        override fun onAdd() {
            initOrderList()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_list)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_order_add -> {
                Toast.makeText(this, "주문 추가 화면 이동", Toast.LENGTH_SHORT).show()

                val dialog = OrderDialog(this, listener!!)
                dialog.show()
//                val builder = AlertDialog.Builder(this)
//                val dialogView = layoutInflater.inflate(R.layout.dialog_add_order, null)
//
//                val dialogSaveBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_save_btn)
//                val dialogCancelBtn = dialogView.findViewById<Button>(R.id.dialog_add_customer_cancel_btn)
//
//                val nameEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_name)
//                val mobileEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_mobile)
//                val telEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_tel)
//                val emailEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_email)
//                val otherEdit = dialogView.findViewById<EditText>(R.id.dialog_add_customer_other)
//
//                builder.setView(dialogView)
//                    .setCancelable(false)
//
//
//                val dialog = builder.show()
//
//                dialogSaveBtn.setOnClickListener { view:View ->
//
//                }
//
//                dialogCancelBtn.setOnClickListener { view : View ->
//                    val builder2 = AlertDialog.Builder(this)
//                    builder2
//                        .setMessage(R.string.cancel_add_order)
//                        .setPositiveButton(R.string.confirm) { dialogInterface: DialogInterface?, i: Int ->
//                            if(dialog.isShowing) {
//                                dialog.dismiss()
//                            }
//                        }
//                        .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
//                        }.show()
//                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {

        mDb = DB(applicationContext).getInstance(applicationContext)

        order_list_toolbar.setTitle(R.string.title_order_list)

        setSupportActionBar(order_list_toolbar)

        order_list_toolbar.setNavigationIcon(R.drawable.back_icon)
        order_list_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        initOrderList()
    }

    fun initOrderList() {
        var list = mDb?.getOrder()

        mAdapter = OrderListAdapter(this, list!!, listener!!)

        order_list.adapter = mAdapter

        if(mAdapter.count > 0) {
            order_list_no.visibility = View.GONE
            order_list_layout.visibility = View.VISIBLE
        } else {
            order_list_no.visibility = View.VISIBLE
            order_list_layout.visibility = View.GONE
        }
    }
}
package com.ian.ordernote

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ian.ordernote.core.CommonActivity
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.data.OrderInfo
import com.ian.ordernote.db.DB
import com.ian.ordernote.dialog.CustomerSelectDialog
import com.ian.ordernote.dialog.OrderDialog
import com.ian.ordernote.view.CustomerListAdapter
import com.ian.ordernote.view.OrderListAdapter
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity: CommonActivity() {

    var mDb : DB? = null
    var orderDailog : OrderDialog? = null
    var customerSelectDialog : CustomerSelectDialog? = null
    lateinit var mAdapter: OrderListAdapter

    interface OrderInfoListener {
        fun onDelete()
        fun onAdd()
        fun showOrderInfo(orderinfo: OrderInfo)
        fun showCustomerSelect()
        fun selectCustomer(customerInfo: CustomerInfo)
    }

    val listener: OrderInfoListener? = object : OrderInfoListener {
        override fun onDelete() {
            initOrderList()
        }

        override fun onAdd() {
            initOrderList()
        }

        override fun showOrderInfo(orderinfo: OrderInfo) {
            showOrderinfoDailog(orderinfo)
//            orderDailog = OrderDialog(this, this)
//            orderDailog!!.show()
        }

        override fun showCustomerSelect() {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            showCustomerSelectDailog()
        }

        override fun selectCustomer(customerInfo: CustomerInfo) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            selectCustomerToOrder(customerInfo)
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

//                orderDailog = OrderDialog(this, listener!!)
//                orderDailog!!.show()
                showOrderinfoDailog(null)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            Const.REQUEST_CODE_IMAGE -> {
                data?.let {
                    Log.e("151515", "")
                    sendImage(it!!.data)
                }
            }
        }
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

    fun sendImage(imgUri: Uri) {
//        var imagePath = getRealPathFromURI(imgUri)
//        Log.e("151515", "imagePath = " + imagePath)
        if(orderDailog != null && orderDailog!!.isShowing) {
            orderDailog!!.setImageUri(imgUri)
        }
    }

    fun getRealPathFromURI(uri: Uri?): String? {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor = this.contentResolver.query(uri, proj, null, null, null)
        var cursor = managedQuery(uri, proj, null, null, null)
        var index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        var index2 = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()
        cursor.moveToFirst()

        Log.e("151515", "c = " + c.getString(index))
        Log.e("151515", "cursor = " + cursor.getString(index))

        var result = c.getString(index)

        return result
    }

    fun showOrderinfoDailog(orderinfo: OrderInfo?) {
        if(orderDailog !=null) {
            if(orderDailog!!.isShowing) {
                orderDailog!!.dismiss()
            }
            orderDailog = null
        }
        orderDailog = OrderDialog(this, listener!!)

        orderinfo?.let {
            orderDailog!!.setOrderInfo(it)
            orderDailog!!.isAdd = false
        }
        orderDailog!!.show()
    }

    fun showCustomerSelectDailog() {
        if(customerSelectDialog != null) {
            if(customerSelectDialog!!.isShowing) {
                customerSelectDialog!!.dismiss()
            }
            customerSelectDialog = null
        }
        customerSelectDialog = CustomerSelectDialog(this, listener!!)
        customerSelectDialog!!.show()
    }

    fun selectCustomerToOrder(customerInfo: CustomerInfo) {
        if(customerSelectDialog!!.isShowing) {
            customerSelectDialog!!.dismiss()
        }
        orderDailog?.selectCustomer(customerInfo)
    }
}
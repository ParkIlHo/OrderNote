package com.ian.ordernote.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.ian.ordernote.CustomerListActivity
import com.ian.ordernote.R
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.db.DB

class CustomerListAdapter(val context: Context, val customerList: ArrayList<CustomerInfo>, val listener: CustomerListActivity.CustomerChangeListener) : BaseAdapter() {
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
        val view = LayoutInflater.from(context).inflate(R.layout.item_customer_list, null)

        val name = view.findViewById<TextView>(R.id.item_customer_list_name)
        val mobile = view.findViewById<TextView>(R.id.item_customer_list_mobile)
        val delBtn = view.findViewById<Button>(R.id.item_customer_list_delete)

        val customerInfo = customerList[position]

        name.text = customerInfo.name
        mobile.text = customerInfo.mobile

        delBtn.setOnClickListener { view: View ->
            val builder2 = AlertDialog.Builder(context)

            builder2
                .setMessage(context.getString(R.string.delete_customer, customerInfo.name, customerInfo.mobile))
                .setPositiveButton(R.string.delete) { dialogInterface: DialogInterface?, i: Int ->
                        //delete
                        DB(context).getInstance(context).delCustomer(customerInfo)
                        listener.onDelete()
                    }
                    .setNegativeButton(R.string.cancel) { dialogInterface: DialogInterface?, i: Int ->
                        //cancel
                    }
                    .show()
        }

        return view
    }
}
package com.ian.ordernote.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ian.ordernote.R
import com.ian.ordernote.data.CustomerInfo

class CustomerListAdapter(val context: Context, val customerList: ArrayList<CustomerInfo>) : BaseAdapter() {
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

        val customerInfo = customerList[position]

        name.text = customerInfo.name
        mobile.text = customerInfo.mobile

        return view
    }
}
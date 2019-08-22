package com.ian.ordernote.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.ian.ordernote.OrderListActivity
import com.ian.ordernote.R
import com.ian.ordernote.core.CommonAlertDialog
import com.ian.ordernote.data.CustomerInfo
import com.ian.ordernote.db.DB
import com.ian.ordernote.view.CustomerListAdapter
import com.ian.ordernote.view.CustomerSelectListAdapter

class CustomerSelectDialog(context: Context, listener: OrderListActivity.OrderInfoListener) : CommonAlertDialog(context), View.OnClickListener {

    lateinit var mDb: DB
    lateinit var mContext: Context
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mDialogView: View

    lateinit var mSearchEdit: EditText
    lateinit var mNameRadio: RadioButton
    lateinit var mTelRadio: RadioButton
    lateinit var mList: ListView
    lateinit var mAdapter: CustomerSelectListAdapter
    lateinit var mSearchBtn: Button

    lateinit var mCustomerList: ArrayList<CustomerInfo>

    lateinit var noDataText : TextView
    lateinit var listLayout : ViewGroup

    var mListener: OrderListActivity.OrderInfoListener?

    init {
        mDb = DB(context).getInstance(context!!)
        mListener = listener
        mContext = context!!
        mDialogView = layoutInflater.inflate(R.layout.dialog_customer_select, null)
        setView(mDialogView)

        mSearchBtn = mDialogView.findViewById<Button>(R.id.dialog_customer_select_cancel_btn)
        noDataText = mDialogView.findViewById<TextView>(R.id.dialog_customer_select_no_data)
        listLayout = mDialogView.findViewById<LinearLayout>(R.id.dialog_customer_select_list_layout)

        mList = mDialogView.findViewById(R.id.dialog_customer_select_list)

        mNameRadio = mDialogView.findViewById(R.id.dialog_customer_select_name)
        mTelRadio = mDialogView.findViewById(R.id.dialog_customer_select_tel)

        mNameRadio.isChecked = true

        mSearchBtn.setOnClickListener(this)
        mDialogView.findViewById<Button>(R.id.dialog_customer_select_search_btn).setOnClickListener(this)

        object : AsyncTask<String, String, Boolean>() {
            override fun onPreExecute() {
                super.onPreExecute()

                mSearchBtn.isEnabled = false
            }

            override fun doInBackground(vararg params: String?): Boolean {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                mCustomerList = mDb.getCustomer()!!

                Log.e("151515", "Customer List size = " + mCustomerList.size)

                return true
            }

            override fun onPostExecute(result: Boolean?) {
                super.onPostExecute(result)
                mSearchBtn.isEnabled = true

                initList()

//                mAdapter = CustomerSelectListAdapter(mContext, mCustomerList!!, mListener!!)
//
//                mList.adapter = mAdapter
            }
        }.execute()
    }

    override fun show() {
        super.show()
    }

    override fun onClick(view: View?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        view?.id.let {
            when(it) {
                R.id.dialog_customer_select_cancel_btn -> {
                    dismiss()
                }

                R.id.dialog_customer_select_search_btn -> {
                    // 검색
                }
            }
        }
    }

    class ListUpdateAsync() : AsyncTask<String?, String?, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
        }
    }

    fun initList() {

        if(mCustomerList == null || mCustomerList.size <= 0) {

            noDataText.visibility = View.VISIBLE
            listLayout.visibility = View.GONE
        } else {
            noDataText.visibility = View.GONE
            listLayout.visibility = View.VISIBLE
            mAdapter = CustomerSelectListAdapter(mContext, mCustomerList!!, mListener!!)
            mList.adapter = mAdapter
        }
    }
}
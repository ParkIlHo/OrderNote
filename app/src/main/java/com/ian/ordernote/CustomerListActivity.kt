package com.ian.ordernote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.activity_order_list.*

class CustomerListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_customer_list)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.customer_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_customer_add -> {
                Toast.makeText(this, "주문자 추가 화면 이동", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        customer_list_toolbar.setTitle(R.string.title_custmer_list)

        setSupportActionBar(customer_list_toolbar)

        customer_list_toolbar.setNavigationIcon(R.drawable.back_icon)
        customer_list_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
}
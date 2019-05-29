package com.ian.ordernote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity: AppCompatActivity() {
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
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        order_list_toolbar.setTitle(R.string.title_order_list)

        setSupportActionBar(order_list_toolbar)

        order_list_toolbar.setNavigationIcon(R.drawable.back_icon)
        order_list_toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }
}
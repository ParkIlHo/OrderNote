package com.ian.ordernote

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onClick(view: View?) {

        when(view?.id) {
            R.id.main_order_btn -> {
                val intent = Intent(this, OrderListActivity::class.java)
                startActivity(intent)
            }
            R.id.main_cunsumer_btn -> {
                val intent = Intent(this, CustomerListActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun init() {
        setSupportActionBar(main_toolbar)

        main_order_btn.setOnClickListener(this)
        main_cunsumer_btn.setOnClickListener(this)
    }
}

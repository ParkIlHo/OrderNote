package com.ian.ordernote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ian.ordernote.core.OrderNotePrefs
import com.ian.ordernote.util.ContextExtentions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity: AppCompatActivity(), View.OnClickListener {

    var prefs: OrderNotePrefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        init()
    }

    override fun onClick(view: View?) {
        Log.e("151515", "testtest")
        var curPw = prefs?.password
        var inputPw = password.editableText.toString()

        Log.e("151515", "curPw = $curPw, inputPw = $inputPw")

        if(curPw.equals(inputPw)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Password error
            // EditText 초기화
            // Password error toast 생성
            password.setText("", TextView.BufferType.EDITABLE)
            Toast.makeText(this, R.string.wrong_pw, Toast.LENGTH_SHORT).show()
        }

    }

    fun init() {
        prefs = OrderNotePrefs(this)
        password.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun beforeTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(input: Editable?) {
                if(input?.length!! >= 4) {
                    confirm_btn.visibility = View.VISIBLE
                } else {
                    confirm_btn.visibility = View.INVISIBLE
                }
            }
        })
        confirm_btn.setOnClickListener(this)
    }

}

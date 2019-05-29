package com.ian.ordernote.util

import android.content.Context
import android.widget.Toast

class ContextExtentions {
    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
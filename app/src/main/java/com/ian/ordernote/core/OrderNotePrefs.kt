package com.ian.ordernote.core

import android.content.Context

class OrderNotePrefs(context: Context) {

    val PREFS_FILE_NAME = "order_note_prefs"

    val PREF_KEY_PASSWORD = "pref_password"

    val prefs = context.getSharedPreferences(PREFS_FILE_NAME, 0)

    var password: String
        get() = prefs.getString(PREF_KEY_PASSWORD, "0000")
        set(value) = prefs.edit().putString(PREF_KEY_PASSWORD, value).apply()
}
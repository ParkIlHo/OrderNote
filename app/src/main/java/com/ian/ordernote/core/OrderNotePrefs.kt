package com.ian.ordernote.core

import android.content.Context

class OrderNotePrefs(context: Context) {

    val PREFS_FILE_NAME = "order_note_prefs"

    val PREF_KEY_PASSWORD = "pref_password"
    val PREF_KEY_PAUSE_TIME = "pref_pase_time"
    var PREF_SCREEN_LOCK = "pref_screen_lock"
    var PREF_SCREEN_LOCK_TIME = "pref_screen_lock_time"

    val prefs = context.getSharedPreferences(PREFS_FILE_NAME, 0)

    var password: String
        get() = prefs.getString(PREF_KEY_PASSWORD, "0000")
        set(value) = prefs.edit().putString(PREF_KEY_PASSWORD, value).apply()

    var pauseTime: Long
        get() = prefs.getLong(PREF_KEY_PAUSE_TIME, 0)
        set(value) = prefs.edit().putLong(PREF_KEY_PAUSE_TIME, value).apply()
    var isScreenLock: Boolean
        get() = prefs.getBoolean(PREF_SCREEN_LOCK, true)
        set(value) = prefs.edit().putBoolean(PREF_SCREEN_LOCK, value).apply()
    var screenLockTime: Int
        get() = prefs.getInt(PREF_SCREEN_LOCK_TIME, 30)
        set(value) = prefs.edit().putInt(PREF_SCREEN_LOCK_TIME, value).apply()
}
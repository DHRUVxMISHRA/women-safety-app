package com.example.womensafetyapp.utils

import android.content.Context

class SessionManager(context : Context) {

    private val prefs =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveUserId(userId : String) {
        prefs.edit().putString("USER_ID", userId).apply()
    }

    fun getUserId() : String? {
        return prefs.getString("USER_ID", null)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
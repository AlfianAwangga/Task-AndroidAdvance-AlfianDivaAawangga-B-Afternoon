package com.example.task_androidadvance.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("simpan", Context.MODE_PRIVATE)

    fun getAudioSetting(): Int = prefs.getInt("audio", 0)
    fun getSfxSetting(): Int = prefs.getInt("sfx", 0)
    fun getNotifSetting(): Int = prefs.getInt("notif", 0)

    fun saveAudioSetting(status: Int) {
        val editor = prefs.edit()
        editor.putInt("audio", status)
        editor.apply()
    }

    fun saveSfxSetting(status: Int) {
        val editor = prefs.edit()
        editor.putInt("sfx", status)
        editor.apply()
    }

    fun saveNotifSetting(status: Int) {
        val editor = prefs.edit()
        editor.putInt("notif", status)
        editor.apply()
    }
}
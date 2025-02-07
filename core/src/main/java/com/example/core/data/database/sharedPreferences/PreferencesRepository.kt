package com.example.core.data.database.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchDone() {
        sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }
}
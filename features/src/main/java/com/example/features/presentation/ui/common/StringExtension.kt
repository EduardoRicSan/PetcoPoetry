package com.example.features.presentation.ui.common

import java.text.SimpleDateFormat
import java.util.Locale

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.toDateLong(): Long {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.parse(this)?.time ?: 0L
}
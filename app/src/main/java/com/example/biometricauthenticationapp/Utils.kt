package com.example.biometricauthenticationapp

import android.content.Context
import android.os.Build
import android.widget.Toast

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

inline fun sdkInt(aboveVersion9: () -> Unit, belowVersion10: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        aboveVersion9.invoke()
    } else {
        belowVersion10.invoke()
    }
}
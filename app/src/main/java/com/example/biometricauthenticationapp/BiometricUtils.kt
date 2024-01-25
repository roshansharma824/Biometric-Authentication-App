package com.example.biometricauthenticationapp

import android.os.Build
import androidx.biometric.BiometricManager

inline fun authenticators(aboveVersion9: () -> Int, belowversion10: () -> Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        aboveVersion9.invoke()
    } else {
        belowversion10.invoke()
    }
}

inline fun BiometricManager.checkExistence(
    onSuccess: (Int) -> Unit,
    onError: (String) -> Unit,
    openSetting: () -> Unit
) {

    val authenticators = authenticators(aboveVersion9 = {
        BiometricManager.Authenticators.BIOMETRIC_STRONG
    }, belowversion10 = {
        BiometricManager.Authenticators.BIOMETRIC_WEAK
    })

    when (canAuthenticate(authenticators)) {

        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
            onError.invoke("BIOMETRIC_ERROR_HW_UNAVAILABLE")
        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            openSetting.invoke()
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            onError.invoke("BIOMETRIC_ERROR_NO_HARDWARE")
        }

        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
            onError.invoke("BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED")
        }

        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
            onError.invoke("BIOMETRIC_ERROR_UNSUPPORTED")
        }

        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
            onError.invoke("BIOMETRIC_STATUS_UNKNOWN")
        }

        BiometricManager.BIOMETRIC_SUCCESS -> {
            onSuccess.invoke(authenticators)
        }
    }

}


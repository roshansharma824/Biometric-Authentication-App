package com.example.biometricauthenticationapp

import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.biometricauthenticationapp.ui.theme.BiometricAuthenticationAppTheme

val LocalActivity = compositionLocalOf<FragmentActivity> { error("error") }

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiometricAuthenticationAppTheme {
                CompositionLocalProvider(
                    LocalActivity provides this
                ) {
                    BiometricAuth()
                }

            }
        }
    }
}

@Composable
fun BiometricAuth() {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val biometricManager = androidx.biometric.BiometricManager.from(context)


    val resultCode = remember {
        mutableIntStateOf(Int.MIN_VALUE)
    }

    val isAuthenticated = remember {
        mutableStateOf(false)
    }

    val executor = ContextCompat.getMainExecutor(context)
    val launcherIntent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            when (result.resultCode) {
                1 -> {
                    resultCode.intValue = 1
                    context.showToast("Enrollment done")
                }

                2 -> {
                    context.showToast("Rejected")
                }

                else -> {
                    context.showToast("User cancel the Enrollment")
                }
            }
        })

    if (isAuthenticated.value) {
        AuthenticationSuccess()
    } else {
        AuthenticationFailed()
    }


    LaunchedEffect(key1 = resultCode.intValue, block = {
        biometricManager.checkExistence(onSuccess = {
            val biometricPromptInfo = BiometricPrompt.PromptInfo.Builder().setTitle("Authenticate")
                .setSubtitle("Authenticate to open App")
                .setNegativeButtonText("Cancel")
                .setAllowedAuthenticators(it)
                .build()
            val biometricPrompt =
                BiometricPrompt(
                    activity,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            super.onAuthenticationError(errorCode, errString)
                            context.showToast("Error $errString")
                            isAuthenticated.value = false
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            context.showToast("Failed")
                            isAuthenticated.value = false
                        }

                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
//                            context.showToast("Succeed")

                            isAuthenticated.value = true
                        }
                    })
            val secretKey = generateSecretKey()
            val cipher = cipher(secretKey)
            val cryptoObject = BiometricPrompt.CryptoObject(cipher)
            biometricPrompt.authenticate(biometricPromptInfo, cryptoObject)
        }, openSetting = {
            sdkInt(aboveVersion9 = {
                val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG
                    )
                }
                launcherIntent.launch(intent)
            }, belowVersion10 = {
                val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
                activity.startActivity(intent)
            })
        }, onError = {
            context.showToast(it)
        })
    })


}
# Biometric Authentication

On this simple project I explored how biometric authentication is implemented on Android.

Biometric authentication is one of the available method to protect sensitive information within our app. The biometric authentication helps us to utilize fingerprint recognition and face recognition sensor on the device (if any).

# ScreenShots
| Success Screen                 | Failed Screen                 |
|--------------------------------|-------------------------------| 
| <img src="assets/success.jpg"> | <img src="assets/failed.jpg"> |

# Dependencies
To integrate Biometric Authentication on our project the steps are simple. Firstly, we need to add the following line under the dependencies tag in build.gradle at the app level.

~~~gradle
    implementation("androidx.biometric:biometric-ktx:1.2.0-alpha05")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
~~~

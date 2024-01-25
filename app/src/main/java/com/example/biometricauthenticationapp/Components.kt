package com.example.biometricauthenticationapp

import android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthenticationSuccess(){

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .background(color = Color(0xFF26A212), shape = RoundedCornerShape(size = 40.dp))
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(0.dp)
                    .width(26.66667.dp)
                    .height(23.44333.dp)
            )
        }

        Text(
            text = "Authentication successfully \ncompleted",

            // Title
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,

                fontWeight = FontWeight(500),
                color = Color(0xFF1A1A1A),
                textAlign = TextAlign.Center,
                letterSpacing = 0.3.sp,
            )
        )
    }

}


@Composable
fun AuthenticationFailed(){

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .background(color = Color(0xFFFF3B30), shape = RoundedCornerShape(size = 40.dp))
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(1.dp)
                    .width(72.dp)
                    .height(72.dp)
            )
        }

        Text(
            text = " Authentication failed ",

            // Title
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,

                fontWeight = FontWeight(500),
                color = Color(0xFF1A1A1A),
                textAlign = TextAlign.Center,
                letterSpacing = 0.3.sp,
            )
        )
    }

}
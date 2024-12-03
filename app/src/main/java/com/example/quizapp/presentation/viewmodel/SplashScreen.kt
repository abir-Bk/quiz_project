package com.example.quizapp.presentation.viewmodel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun SplashScreen(navController: androidx.navigation.NavController) {
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.Companion
            .fillMaxSize()
    ) {

        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = com.example.quizapp.R.drawable.background1),
            contentDescription = "Splash Screen",
            contentScale = androidx.compose.ui.layout.ContentScale.Companion.Crop,
            modifier = androidx.compose.ui.Modifier.Companion.fillMaxSize()
        )

        androidx.compose.foundation.layout.Column(
            modifier = androidx.compose.ui.Modifier.Companion
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.Companion.CenterHorizontally
        ) {

        }
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier.Companion
                .fillMaxWidth()
                .padding(16.dp)
                .align(androidx.compose.ui.Alignment.Companion.BottomCenter)
        ) {
            androidx.compose.material3.Button(
                onClick = {
                    navController.navigate("quiz_screen")
                },
                modifier = androidx.compose.ui.Modifier.Companion.fillMaxWidth()
            ) {
                androidx.compose.material3.Text(
                    text = "Start Quiz",
                    color = androidx.compose.ui.graphics.Color.Companion.White
                )
            }
        }
    }
}

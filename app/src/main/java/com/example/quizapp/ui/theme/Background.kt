package com.example.quizapp.ui.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun Background(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = com.example.quizapp.R.drawable.point),
            contentDescription = "Splash Screen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
}}

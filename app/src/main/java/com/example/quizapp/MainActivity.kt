package com.example.quizapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.quizapp.data.remote.RetrofitInstance
import com.example.quizapp.domain.usecase.GetQuestionUseCase
import com.example.quizapp.data.repository.QuizRepositoryImpl
import com.example.quizapp.presentation.viewmodel.QuizViewModel
import com.example.quizapp.presentation.viewmodel.SplashScreen
import com.example.quizapp.ui.theme.QuizappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = RetrofitInstance.api
        val repository = QuizRepositoryImpl(api)
        val useCase = GetQuestionUseCase(repository)
        val quizViewModel = QuizViewModel(useCase)
        Log.d("MainActivity", "ViewModel initialized: $quizViewModel")

        setContent {
            QuizappTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen") {
                    composable("splash_screen") {
                        SplashScreen(navController = navController)
                    }
                    composable("quiz_screen") {

                        QuizScreen(viewModel = quizViewModel)
                    }

                }
            }
        }
    }
}

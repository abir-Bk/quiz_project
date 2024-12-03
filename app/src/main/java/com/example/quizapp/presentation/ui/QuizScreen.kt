package com.example.quizapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.presentation.viewmodel.QuizViewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.quizapp.R

@Composable
fun QuizScreen(viewModel: QuizViewModel) {
    val questions = viewModel.questions.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val currentQuestionIndex = remember { mutableStateOf(0) }
    val selectedAnswer = remember { mutableStateOf<String?>(null) }
    val isCorrect = remember { mutableStateOf<Boolean?>(null) }
    val score = remember { mutableStateOf(0) }
    val showAlert = remember { mutableStateOf(false) }
    val showFinalScore = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.step4),
            contentDescription = "Splash Screen",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = {
                    viewModel.fetchQuestions(10, "science,history")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Click for Start")
            }

            Spacer(modifier = Modifier.height(16.dp))

            error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (questions.isNotEmpty()) {
                val question = questions[currentQuestionIndex.value]
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.fillMaxWidth(),
                    color = androidx.compose.ui.graphics.Color.White
                )

                val allAnswers =
                    (listOf(question.correctAnswer) + question.incorrectAnswers).shuffled()

                Spacer(modifier = Modifier.height(16.dp))

                allAnswers.forEach { answer ->
                    Button(
                        onClick = {
                            selectedAnswer.value = answer
                            isCorrect.value = answer == question.correctAnswer
                            if (isCorrect.value == true) {
                                score.value++
                            }
                            showAlert.value = true
                        },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    ) {
                        Text(text = answer)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (showAlert.value) {
                    val resultMessage = if (isCorrect.value == true) {
                        "Correct!"
                    } else {
                        "Incorrect. The correct answer is: ${question.correctAnswer}"
                    }

                    AlertDialog(
                        onDismissRequest = {
                            showAlert.value = false
                        },
                        title = {
                            Text("Result")
                        },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val icon = if (isCorrect.value == true) {
                                    Icons.Filled.Check
                                } else {
                                    Icons.Filled.Close
                                }

                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Result Icon",
                                    tint = if (isCorrect.value == true) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = resultMessage,
                                    style = MaterialTheme.typography.bodyMedium.copy(color = androidx.compose.ui.graphics.Color.Black)
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if (currentQuestionIndex.value < questions.size - 1) {
                                        currentQuestionIndex.value++
                                    } else {
                                        showFinalScore.value = true
                                    }
                                    selectedAnswer.value = null
                                    isCorrect.value = null
                                    showAlert.value = false
                                }
                            ) {
                                Text("Next Question")
                            }
                        }
                    )
                }
                if (showFinalScore.value) {
                    AlertDialog(
                        onDismissRequest = {
                            showFinalScore.value = false
                        },
                        title = {
                            Text("Quiz Finished")
                        },
                        text = {
                            Text(
                                text = "Your total score is: ${score.value}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = androidx.compose.ui.graphics.Color.Black)
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showFinalScore.value = false
                                    score.value = 0  // Reset score
                                    currentQuestionIndex.value = 0
                                    viewModel.fetchQuestions(10, "science,history")
                                }
                            ) {
                                Text("Replay")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showFinalScore.value = false
                                }
                            ) {
                                Text("Quit")
                            }
                        }
                    )
                }
            }
        }
    }
}

package com.example.quizapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.usecase.GetQuestionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(private val getQuestionUseCase: GetQuestionUseCase) : ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchQuestions(limit: Int, categories: String) {
        viewModelScope.launch {
            try {
                Log.d("QuizViewModel", "Fetching questions with limit: $limit and categories: $categories")

                // Call the use case to fetch questions (handles both local and remote sources)
                val response = getQuestionUseCase(limit, categories)

                if (response.isNotEmpty()) {
                    Log.d("QuizViewModel", "Questions fetched successfully: ${response.size}")
                    _questions.value = response
                } else {
                    _error.value = "No questions available."
                    Log.d("QuizViewModel", "No questions available.")
                }
            } catch (e: Exception) {
                _error.value = "Error fetching questions: ${e.localizedMessage}"
                Log.e("QuizViewModel", "Error fetching questions", e)
            }
        }
    }
}

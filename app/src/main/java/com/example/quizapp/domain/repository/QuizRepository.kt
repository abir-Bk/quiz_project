package com.example.quizapp.domain.repository

import com.example.quizapp.domain.model.Question

interface QuizRepository {
    suspend fun getQuestionsFromRemote(limit: Int, categories: String): List<Question>
    suspend fun getQuestionsFromLocal(): List<Question>
    suspend fun saveQuestionsToLocal(questions: List<Question>)
}

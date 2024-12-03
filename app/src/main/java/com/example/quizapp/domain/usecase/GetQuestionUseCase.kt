package com.example.quizapp.domain.usecase

import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository

class GetQuestionUseCase(private val repository: QuizRepository) {
    suspend operator fun invoke(limit: Int, categories: String): List<Question> {
        return try {
            val remoteQuestions = repository.getQuestionsFromRemote(limit, categories)
            if (remoteQuestions.isNotEmpty()) {
                repository.saveQuestionsToLocal(remoteQuestions)
            }
            remoteQuestions
        } catch (e: Exception) {
            repository.getQuestionsFromLocal()
        }
    }
}

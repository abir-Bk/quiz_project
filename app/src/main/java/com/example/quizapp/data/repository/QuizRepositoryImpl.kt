package com.example.quizapp.data.repository

import com.example.quizapp.data.local.QuestionDao
import com.example.quizapp.data.model.toDomain
import com.example.quizapp.data.remote.QuizApi
import com.example.quizapp.domain.model.Question
import com.example.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepositoryImpl(
    private val api: QuizApi,
    private val dao: QuestionDao
) : QuizRepository {

    override suspend fun getQuestionsFromRemote(limit: Int, categories: String): List<Question> {
        return withContext(Dispatchers.IO) {
            val remoteQuestions = api.getQuestions(limit, categories)
            remoteQuestions.map { it.toDomain() }
        }
    }

    override suspend fun getQuestionsFromLocal(): List<Question> {
        return withContext(Dispatchers.IO) {
            dao.getAllQuestions().map { it.toDomain() }
        }
    }

    override suspend fun saveQuestionsToLocal(questions: List<Question>) {
        withContext(Dispatchers.IO) {
            dao.insertQuestions(questions)
        }
    }
}


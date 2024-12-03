package com.example.quizapp.data.remote

import com.example.quizapp.data.model.Question
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("api/questions")
    suspend fun getQuestions(
        @Query("limit") limit: Int,
        @Query("categories") categories: String
    ): List<Question>
}

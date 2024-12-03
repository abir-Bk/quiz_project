package com.example.quizapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizapp.data.model.Question

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<com.example.quizapp.domain.model.Question>)

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<Question>
}


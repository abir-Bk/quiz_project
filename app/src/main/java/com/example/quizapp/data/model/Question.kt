package com.example.quizapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "options") val options: List<String>,
    @ColumnInfo(name = "correctAnswer") val correctAnswer: String
)
fun Question.toDomain(): com.example.quizapp.domain.model.Question {
    return com.example.quizapp.domain.model.Question(
        id = id.toString(),
        question = question,
        correctAnswer = correctAnswer,
        incorrectAnswers = options.filter { it != correctAnswer },
        category = "" )
}

fun com.example.quizapp.domain.model.Question.toData(): Question {
    return Question(
        question = question,
        options = listOf(correctAnswer) + incorrectAnswers,
        correctAnswer = correctAnswer
    )
}

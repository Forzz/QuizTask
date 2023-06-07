package com.forzz.quiztask.model

import java.io.Serializable

data class Result(
    val quizElements: List<QuizElement>,
    val time: String,
    val correctAnswers: Int
) : Serializable

data class QuizElement(
    val question: String,
    val rightAnswer: String,
    var isCorrectAnswer: Boolean
)

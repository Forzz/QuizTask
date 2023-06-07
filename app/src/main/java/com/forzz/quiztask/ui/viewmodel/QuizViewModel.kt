package com.forzz.quiztask.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.forzz.quiztask.common.Constants
import com.forzz.quiztask.model.QuizElement
import kotlin.random.Random

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val books = Constants.bookList
    private var startTime: Long = 0
    var endTime: Long = 0

    val allQuestions = Constants.questionList.toMutableList()
    var currentOptions = mutableListOf<String>()
    val currentQuiz = MutableLiveData<QuizElement>()
    val result = mutableListOf<QuizElement>()

    init {
        startTime = System.currentTimeMillis()
    }

    fun generateQuiz() {

        currentOptions = mutableListOf()
        val rightIndex = getRandomIndex(0, 2)

        val randomQuizElement = if (allQuestions.size != 1)
            allQuestions[getRandomIndex(0, allQuestions.size - 1)] else allQuestions[0]

        allQuestions.remove(randomQuizElement)

        val booksCopy = books.toMutableList()
        booksCopy.remove(randomQuizElement.rightAnswer)

        /*
        Filling the list with random image names for one quiz (3 images on screen)
        Заполняем список вопросов двумя случайными неправильными элементами и одним случайным правильным
        */
        for (i in 0..2) {
            val randomOption = if (i == rightIndex) {
                randomQuizElement.rightAnswer
            } else {
                booksCopy.removeAt(getRandomIndex(0, booksCopy.size - 1))
            }

            currentOptions.add(randomOption)
        }

        currentQuiz.value = randomQuizElement
    }

    fun changeStatus(userAnswer: String) {
        currentQuiz.value?.isCorrectAnswer = userAnswer == currentQuiz.value?.rightAnswer
        addToResult()
    }

    fun calculateTimeSpent(): String {
        val timeSpentInMillis = endTime - startTime
        val timeSpentInSeconds = timeSpentInMillis / 1000
        return String.format("%02d:%02d", timeSpentInSeconds / 60, timeSpentInSeconds % 60)
    }

    fun getCorrectAnswers(): Int = result.count { it.isCorrectAnswer }

    private fun addToResult() {
        currentQuiz.value?.let { result.add(it) }
    }

    private fun getRandomIndex(start: Int, end: Int): Int {
        return Random.nextInt(start, end)
    }
}
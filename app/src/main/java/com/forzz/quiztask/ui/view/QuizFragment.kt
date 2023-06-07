package com.forzz.quiztask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.forzz.quiztask.databinding.FragmentQuizBinding
import com.forzz.quiztask.model.Result
import com.forzz.quiztask.ui.viewmodel.QuizViewModel

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val quizViewModel: QuizViewModel by viewModels()

    private var resourceId1: Int = 0
    private var resourceId2: Int = 0
    private var resourceId3: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQuizBinding.inflate(inflater, container, false)

        if (savedInstanceState != null) {
            resourceId1 = savedInstanceState.getInt("resourceId1")
            resourceId2 = savedInstanceState.getInt("resourceId2")
            resourceId3 = savedInstanceState.getInt("resourceId3")
        } else {
            generateQuiz()
        }

        quizViewModel.currentQuiz.observe(viewLifecycleOwner) {

            binding.tvQuizBook.text = quizViewModel.currentQuiz.value?.question

            resourceId1 = resources.getIdentifier(
                quizViewModel.currentOptions[0],
                "drawable",
                context?.packageName
            )
            resourceId2 = resources.getIdentifier(
                quizViewModel.currentOptions[1],
                "drawable",
                context?.packageName
            )
            resourceId3 = resources.getIdentifier(
                quizViewModel.currentOptions[2],
                "drawable",
                context?.packageName
            )

            Glide.with(binding.root.context).load(resourceId1).transform(RoundedCorners(12))
                .into(binding.ivBookLogo1)
            Glide.with(binding.root.context).load(resourceId2).transform(RoundedCorners(12))
                .into(binding.ivBookLogo2)
            Glide.with(binding.root.context).load(resourceId3).transform(RoundedCorners(12))
                .into(binding.ivBookLogo3)
        }

        binding.ivBookLogo1.setOnClickListener {
            userAnswer(resourceId1)
            checkQuizStatus()
        }

        binding.ivBookLogo2.setOnClickListener {
            userAnswer(resourceId2)
            checkQuizStatus()
        }

        binding.ivBookLogo3.setOnClickListener {
            userAnswer(resourceId3)
            checkQuizStatus()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("resourceId1", resourceId1)
        outState.putInt("resourceId2", resourceId2)
        outState.putInt("resourceId3", resourceId3)
    }

    private fun generateQuiz() {
        quizViewModel.generateQuiz()
    }

    private fun userAnswer(resourceId: Int) {
        val ivName = getDrawableName(resourceId)
        quizViewModel.changeStatus(ivName)
    }

    private fun checkQuizStatus() {
        if (quizViewModel.allQuestions.size == 0) {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(getQuizResult())
            findNavController().navigate(action)
        } else {
            generateQuiz()
        }
    }

    private fun getQuizResult(): Result {
        quizViewModel.endTime = System.currentTimeMillis()
        return Result(
            quizViewModel.result,
            quizViewModel.calculateTimeSpent(),
            quizViewModel.getCorrectAnswers()
        )
    }

    private fun getDrawableName(resourceId: Int) = resources.getResourceEntryName(resourceId)
}
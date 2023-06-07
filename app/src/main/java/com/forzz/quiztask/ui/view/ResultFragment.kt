package com.forzz.quiztask.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.forzz.quiztask.databinding.FragmentResultBinding
import com.forzz.quiztask.model.QuizElement
import com.forzz.quiztask.ui.adapters.ResultsAdapter


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private var adapter: ResultsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ResultsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        val args: ResultFragmentArgs by navArgs()

        binding.rvResults.layoutManager = LinearLayoutManager(context)
        binding.rvResults.adapter = adapter

        initRecyclerView(args.result.quizElements)

        binding.tvTime.text = args.result.time
        val resultCorrect = "${args.result.correctAnswers}/${args.result.quizElements.size}"
        binding.tvResultNumber.text = resultCorrect


        return binding.root
    }

    private fun initRecyclerView(results: List<QuizElement>) {
        adapter?.setData(results)
    }
}
package com.forzz.quiztask.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forzz.quiztask.R
import com.forzz.quiztask.databinding.ResultItemBinding
import com.forzz.quiztask.model.QuizElement

class ResultsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val results: MutableList<QuizElement> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultItemBinding.inflate(inflater, parent, false)

        return ResultViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ResultViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): QuizElement = results[position]

    fun setData(list: List<QuizElement>) {
        results.clear()
        results.addAll(list)
        notifyDataSetChanged()
    }

    inner class ResultViewHolder(
        private val binding: ResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(quizElement: QuizElement) {
            binding.tvBookResult.text = quizElement.question
            if (quizElement.isCorrectAnswer) {
                Glide.with(binding.root.context).load(R.drawable.right_answer).into(binding.ivResult)
            } else {
                Glide.with(binding.root.context).load(R.drawable.wrong_answer).into(binding.ivResult)
            }
        }
    }

}
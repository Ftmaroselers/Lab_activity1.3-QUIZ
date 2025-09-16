package com.planetquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.planetquiz.databinding.FragmentAnswersBinding

class AnswersFragment : Fragment() {

    private var _binding: FragmentAnswersBinding? = null
    private val binding get() = _binding!!

    private var isCorrect: Boolean = false
    private var question: String? = null
    private var answer: String? = null
    private var detail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isCorrect = it.getBoolean(ARG_IS_CORRECT)
            question = it.getString(ARG_QUESTION)
            answer = it.getString(ARG_ANSWER)
            detail = it.getString(ARG_DETAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnswersBinding.inflate(inflater, container, false)

        // Show the original question
        binding.txtQuestion.text = question

        // Show result message
        binding.txtResult.text = if (isCorrect) {
            "✅ Correct! $answer is the right answer."
        } else {
            "❌ Wrong! You chose $answer.\nThe correct answer is above."
        }

        // Show additional details/explanation
        binding.txtDetail.text = detail

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_IS_CORRECT = "arg_is_correct"
        private const val ARG_QUESTION = "arg_question"
        private const val ARG_ANSWER = "arg_answer"
        private const val ARG_DETAIL = "arg_detail"

        @JvmStatic
        fun newInstance(isCorrect: Boolean, question: String, answer: String, detail: String) =
            AnswersFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_CORRECT, isCorrect)
                    putString(ARG_QUESTION, question)
                    putString(ARG_ANSWER, answer)
                    putString(ARG_DETAIL, detail)
                }
            }
    }
}

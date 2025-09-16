package com.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.planetquiz.databinding.FragmentMultipleChoiceBinding

class MultipleChoiceFragment : Fragment() {

    private var _binding: FragmentMultipleChoiceBinding? = null
    private val binding get() = _binding!!

    private var question: String? = null
    private var correctAnswer: String? = null
    private var detail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString(ARG_QUESTION)
            correctAnswer = it.getString(ARG_CORRECT)
            detail = it.getString(ARG_DETAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultipleChoiceBinding.inflate(inflater, container, false)

        // Show the question text
        binding.txtQuestion.text = question

        // All planet names
        val planets = listOf(
            getString(R.string.mercury),
            getString(R.string.venus),
            getString(R.string.earth),
            getString(R.string.mars),
            getString(R.string.jupiter),
            getString(R.string.saturn),
            getString(R.string.uranus),
            getString(R.string.neptune),
            getString(R.string.pluto)
        )

        // Match buttons to planets
        val buttons = listOf(
            binding.btnMercury, binding.btnVenus, binding.btnEarth, binding.btnMars,
            binding.btnJupiter, binding.btnSaturn, binding.btnUranus, binding.btnNeptune,
            binding.btnPluto
        )

        // Attach listeners
        for (i in planets.indices) {
            buttons[i].text = planets[i]
            buttons[i].setOnClickListener {
                val selected = planets[i]
                val isCorrect = selected == correctAnswer
                openAnswerScreen(isCorrect, question ?: "", selected, detail ?: "")
            }
        }

        return binding.root
    }

    private fun openAnswerScreen(isCorrect: Boolean, question: String, answer: String, detail: String) {
        val fragment = AnswersFragment.newInstance(isCorrect, question, answer, detail)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_QUESTION = "arg_question"
        private const val ARG_CORRECT = "arg_correct"
        private const val ARG_DETAIL = "arg_detail"

        @JvmStatic
        fun newInstance(question: String, correctAnswer: String, detail: String) =
            MultipleChoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUESTION, question)
                    putString(ARG_CORRECT, correctAnswer)
                    putString(ARG_DETAIL, detail)
                }
            }
    }
}

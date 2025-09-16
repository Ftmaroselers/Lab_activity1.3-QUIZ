package com.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.planetquiz.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        _binding = FragmentQuestionBinding.bind(view)

        // Largest planet question
        binding.btnLargestPlanet.setOnClickListener {
            openMultipleChoice(
                question = getString(R.string.question_largest_planet),
                correctAnswer = getString(R.string.jupiter),
                detail = getString(R.string.answer_largest_planet)
            )
        }

        // Most moons question
        binding.btnMostMoons.setOnClickListener {
            openMultipleChoice(
                question = getString(R.string.question_most_moons),
                correctAnswer = getString(R.string.saturn),
                detail = getString(R.string.answer_most_moons)
            )
        }

        // Axis tilt question
        binding.btnSpinsSideways.setOnClickListener {
            openMultipleChoice(
                question = getString(R.string.question_axis_tilt),
                correctAnswer = getString(R.string.uranus),
                detail = getString(R.string.answer_axis_tilt)
            )
        }

        return view
    }

    private fun openMultipleChoice(question: String, correctAnswer: String, detail: String) {
        val fragment = MultipleChoiceFragment.newInstance(question, correctAnswer, detail)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

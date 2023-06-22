package ru.zfix27r.mylearning.ui.question

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(R.layout.fragment_question) {
    private val binding by viewBinding(FragmentQuestionBinding::bind)
    private val viewModel by viewModels<QuestionViewModel>()

    private val callback = object : QuestionAdapterCallback {

    }

    private val adapter = QuestionAdapter(callback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.questionRecyclerView.adapter = adapter
    }
}
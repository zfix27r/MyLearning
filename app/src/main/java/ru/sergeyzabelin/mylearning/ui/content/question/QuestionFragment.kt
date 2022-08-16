package ru.sergeyzabelin.mylearning.ui.content.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentQuestionBinding
import ru.sergeyzabelin.mylearning.ui.content.ContentViewModel
import ru.zfix27r.domain.model.content.ContentDataModel

typealias Question = ContentDataModel.Question

class QuestionFragment(private val viewModel: ContentViewModel) : Fragment() {

    private val binding by viewBinding(FragmentQuestionBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = QuestionAdapter(onListenActionFromAdapter(), onListenContextMenuFromAdapter())

        binding.recycler.adapter = adapter
        viewModel.content.observe(viewLifecycleOwner) {
            adapter.submitList(it.questions)
        }
    }

    private fun onListenActionFromAdapter(): QuestionActionListener {
        return object : QuestionActionListener {

        }
    }

    private fun onListenContextMenuFromAdapter(): View.OnCreateContextMenuListener {
        return View.OnCreateContextMenuListener { menu, v, _ ->  }
    }

}

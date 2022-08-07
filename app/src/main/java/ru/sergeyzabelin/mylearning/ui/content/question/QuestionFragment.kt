package ru.sergeyzabelin.mylearning.ui.content.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.zfix27r.data.model.db.Question
import ru.sergeyzabelin.mylearning.databinding.FragmentQuestionBinding
import ru.sergeyzabelin.mylearning.ui.content.ContentViewModel
import ru.sergeyzabelin.mylearning.utils.autoCleared

class QuestionFragment(private val viewModel: ContentViewModel) : Fragment() {

    private var binding by autoCleared<FragmentQuestionBinding>()
    private var adapter by autoCleared<QuestionAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentQuestionBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuestionAdapter(object : QuestionActionListener {
            override fun onAdd(question: Question) {
            }

            override fun onEdit(question: Question) {
            }

            override fun onDelete(question: Question) {
            }
        })

        binding.recycler.adapter = adapter
        viewModel.content.observe(viewLifecycleOwner) { content ->
            content.data?.let {
                adapter.submitList(it.questions)
            }
        }
    }

}
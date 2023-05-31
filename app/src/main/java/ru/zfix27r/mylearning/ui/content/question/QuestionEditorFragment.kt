package ru.sergeyzabelin.zfix27r.ui.content.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sergeyzabelin.zfix27r.R

class QuestionEditorFragment : Fragment() {

    companion object {
        fun newInstance() = QuestionEditorFragment()
    }

    private lateinit var viewModel: QuestionEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionEditorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
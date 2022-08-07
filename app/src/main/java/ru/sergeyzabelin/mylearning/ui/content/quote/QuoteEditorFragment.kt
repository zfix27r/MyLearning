package ru.sergeyzabelin.mylearning.ui.content.quote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeyzabelin.mylearning.R

class QuoteEditorFragment : Fragment() {

    companion object {
        fun newInstance() = QuoteEditorFragment()
    }

    private lateinit var viewModel: QuoteEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuoteEditorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
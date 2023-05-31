package ru.sergeyzabelin.zfix27r.ui.content.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sergeyzabelin.zfix27r.R

class SourceEditorFragment : Fragment() {

    companion object {
        fun newInstance() = SourceEditorFragment()
    }

    private lateinit var viewModel: SourceEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SourceEditorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
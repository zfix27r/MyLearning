package ru.sergeyzabelin.mylearning.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sergeyzabelin.mylearning.R

class ContentEditFragment : Fragment() {

    companion object {
        fun newInstance() = ContentEditFragment()
    }

    private lateinit var viewModel: ContentEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package ru.sergeyzabelin.mylearning.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.sergeyzabelin.mylearning.R

class ContentAddFragment : Fragment() {

    companion object {
        fun newInstance() = ContentAddFragment()
    }

    private lateinit var viewModel: ContentAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_add, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentAddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
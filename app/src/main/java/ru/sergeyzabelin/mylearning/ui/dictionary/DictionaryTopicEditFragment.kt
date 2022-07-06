package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryTopicEditBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryTopicEditFragment : Fragment() {

    private val viewModel by viewModels<DictionaryTopicEditViewModel>()
    private var binding by autoCleared<FragmentDictionaryTopicEditBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryTopicEditBinding.inflate(inflater, container, false)

        viewModel.topic.observe(viewLifecycleOwner) {
            viewModel.updateSaveTopicModelFromTopic()
            //Log.e("sdf", it.data.toString())
            dataBinding.topic = viewModel.topic
            dataBinding.saveTopicModel = viewModel.saveTopicModel
            //Log.e("sdf", viewModel.saveTopicModel.toString())
        }

        dataBinding.topicTitleLayout.editText?.addTextChangedListener {
            checkInputTitle(it.toString())
        }

        dataBinding.topic = viewModel.topic


        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_app_bar, menu)
    }

    private fun checkInputTitle(title: String) {
        if (title.isEmpty()) {
            binding.topicTitleLayout.error = this.resources.getString(R.string.message_field_empty)
        } else {
            binding.topicTitleLayout.error = null
        }
    }
}
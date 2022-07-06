package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryTopicAddBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryTopicAddFragment : Fragment() {

    private val viewModel by viewModels<DictionaryTopicEditViewModel>()
    private var binding by autoCleared<FragmentDictionaryTopicAddBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryTopicAddBinding.inflate(inflater, container, false)

        //dataBinding.topic = viewModel.data

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dictionary_topic_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dictionaryDone -> {


                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

/*    private fun checkAllInputAndDone() {
        viewModel.saveTopicModel.title = binding.dictionaryAddTitle.text.toString()
        viewModel.saveTopicModel.title = binding.dictionaryAddLabel.text.toString()

        if (viewModel.saveTopicModel.title.isEmpty()) {
            binding.dictionaryAddTitleLayout.error =
                this.resources.getString(R.string.message_field_empty)

            return
        } else {
            binding.dictionaryAddTitleLayout.error = null
        }

        if (viewModel.saveTopicModel.title.isEmpty()) {
            binding.dictionaryAddLabelLayout.error =
                this.resources.getString(R.string.message_field_empty)
            return
        } else {
            binding.dictionaryAddLabelLayout.error = null
        }

        viewModel.addSaveTopicModel()
    }*/
}
package ru.sergeyzabelin.mylearning.ui.dictionary.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryAddBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared

class DictionaryAddFragment : Fragment() {

    private val viewModel by activityViewModels<DictionaryAddViewModel>()
    private var binding by autoCleared<FragmentDictionaryAddBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryAddBinding.inflate(inflater, container, false)
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_dictionary_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dictionaryDone -> {
                checkAllInputAndDone()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkAllInputAndDone() {
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
    }

}
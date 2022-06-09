package ru.sergeyzabelin.mylearning.ui.dictionary.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryAddBinding
import ru.sergeyzabelin.mylearning.ui.main.MainViewModel

class DictionaryAddFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryAddBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentDictionaryAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
/*            R.id.action_done -> {
                checkAllInputAndDone()
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkAllInputAndDone() {
        val titleOriginal = binding.dictionaryAddTitleOriginalInput.text.toString()
        val title = binding.dictionaryAddTitleInput.text.toString()
        val description = binding.dictionaryAddDescriptionInput.text.toString()


        if (titleOriginal.isEmpty()) {
            binding.dictionaryAddTitleOriginal.error =
                this.resources.getString(R.string.message_field_empty)

            return
        } else {
            binding.dictionaryAddTitleOriginal.error = null
        }

        if (title.isEmpty()) {
            binding.dictionaryAddTitle.error =
                this.resources.getString(R.string.message_field_empty)
            return
        } else {
            binding.dictionaryAddTitle.error = null
        }

        if (description.isEmpty()) {
            binding.dictionaryAddDescription.error =
                this.resources.getString(R.string.message_field_empty)
            return
        }
        binding.dictionaryAddDescription.error = null

        //val dictionary = Dictionary(titleOriginal, title, description)

        //viewModel.setDictionary(dictionary)
    }

}
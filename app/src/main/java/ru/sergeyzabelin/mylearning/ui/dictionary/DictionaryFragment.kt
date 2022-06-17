package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.di.Injectable
import ru.sergeyzabelin.mylearning.utils.AppExecutors
import ru.sergeyzabelin.mylearning.utils.autoCleared
import javax.inject.Inject


class DictionaryFragment : Fragment(), Injectable {

    private val navArgs by navArgs<DictionaryFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel by viewModels<DictionaryViewModel> {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoCleared<FragmentDictionaryBinding>()
    var adapter by autoCleared<DictionaryListAdapter>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryBinding.inflate(inflater, container, false)
        binding = dataBinding

        viewModel.topicId = navArgs.topicId
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DictionaryListAdapter { id -> onClickGoToNextChapter(id) }
        binding.dictionaryRecycler.adapter = adapter

        viewModel.topics.observe(viewLifecycleOwner) { list ->
            Log.e("dd", list.data.toString())
            adapter.submitList(list.data)
        }
    }

    fun onClickFab() {
        findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
    }

    private fun onClickGoToNextChapter(id: Long) {
        findNavController().navigate(DictionaryFragmentDirections.actionNavDictionaryFragmentSelf(id))

/*
        findNavController().navigate(
            DictionaryFragmentDirections.actionNavDictionaryFragmentToNavDictionaryDetailFragment(
                id
            )
        )
*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)

        //menu.findItem(R.id.action_done).isVisible = false
    }
}
package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared
import javax.inject.Inject

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val navArgs by navArgs<DictionaryFragmentArgs>()
    private var binding by autoCleared<FragmentDictionaryBinding>()
    private var adapter by autoCleared<DictionaryListAdapter>()

    @Inject
    lateinit var dictionaryViewModelFactory: DictionaryViewModel.DictionaryViewModelFactory

    private val viewModel by viewModels<DictionaryViewModel> {
        DictionaryViewModel.provideFactory(
            dictionaryViewModelFactory,
            this,
            arguments,
            navArgs.topicId
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryBinding.inflate(inflater, container, false)
        binding = dataBinding

        //viewModel.topicId = navArgs.topicId

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
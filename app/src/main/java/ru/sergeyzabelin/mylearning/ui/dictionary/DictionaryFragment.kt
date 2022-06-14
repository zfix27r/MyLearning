package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding


class DictionaryFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var adapter: TopicAdapter

    private val viewModel by viewModels<DictionaryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)


        viewModel.getAllDictionaryGroup()


        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopicAdapter { id -> onClickRecyclerItem(id) }
        binding.dictionaryRecycler.adapter = adapter

        viewModel.topics.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    fun onClickFab() {
        findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
    }

    private fun onClickRecyclerItem(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionNavDictionaryFragmentToNavDictionaryDetailFragment(
                id
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)

        //menu.findItem(R.id.action_done).isVisible = false
    }
}
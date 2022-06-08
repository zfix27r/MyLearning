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
    private lateinit var adapter: DictionaryAdapter

    private val viewModel: DictionaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.fragment = this

        viewModel.getAllDictionaryGroup()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DictionaryAdapter { id -> onClickRecyclerItem(id) }
        binding.dictionaryRecycler.adapter = adapter

        viewModel.dictionaryList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    fun onClickFab() {
        findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
    }

    private fun onClickRecyclerItem(id: Int) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionNavDictionaryFragmentToNavDictionaryDetailFragment(
                id
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        menu.findItem(R.id.action_done).isVisible = false
    }
}
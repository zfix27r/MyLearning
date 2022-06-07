package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.adapters.DictionaryAdapter
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel


class DictionaryFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var adapter: DictionaryAdapter

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dictionaryFab.setOnClickListener {
            findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
        }

        adapter = DictionaryAdapter { position -> onSelectedListener(position) }
        binding.dictionaryRecycler.adapter = adapter

        val itemTouchHelperCallback =
            object : ItemTouchHelper.Callback() {
                private var swipeBack = false

                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    TODO("Not yet implemented")
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    TODO("Not yet implemented")
                }

            }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.dictionaryRecycler)

        viewModel.dictionaryList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun onSelectedListener(position: Int) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        menu.findItem(R.id.action_done).isVisible = false
    }
}
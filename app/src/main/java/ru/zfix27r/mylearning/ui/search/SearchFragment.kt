package ru.zfix27r.mylearning.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchAdapterCallback {
    //private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter = SearchAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareView()
        observers()
    }

    private fun prepareView() {
        //binding.searchRecycler.adapter = adapter
    }

    private fun observers() {
        viewModel.result.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClickItemAdapter(searchAdapterModel: SearchAdapterModel) {
        TODO("Not yet implemented")
    }
}
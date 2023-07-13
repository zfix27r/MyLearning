package ru.zfix27r.mylearning.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainBinding
import ru.zfix27r.mylearning.ui.base.BaseActionModeCallback
import ru.zfix27r.mylearning.ui.base.BaseFragment
import ru.zfix27r.mylearning.ui.base.BaseItemDecoration

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    private val adapter = MainAdapter {
        object : MainAdapterCallback {
            override fun onClick(quoteId: Int) {
                navToQuote(quoteId)
            }

            override fun onLongClick(quoteId: Int) {
                viewQuotesActionMode(quoteId)
            }

            override fun onClickMore(quoteId: Int) {
                TODO("Not yet implemented")
            }
        }
    }
    private val adapterItemDecoration = BaseItemDecoration()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        setFabListener()
        binding.prepView()
        viewModel.attachToBaseViewModel()
        viewModel.observeMain()
    }

    private fun setToolbar() {
        enableScrollTopAppBar()
        searchbar.updateMenu(R.menu.toolbar) {
            when (it.itemId) {
                R.id.toolbar_filter -> {
                    navToFilter()
                    true
                }

                else -> false
            }
        }
    }

    private fun FragmentMainBinding.prepView() {
        mainRecycler.adapter = adapter
        mainRecycler.addItemDecoration(adapterItemDecoration)

        ViewCompat.setOnApplyWindowInsetsListener(mainRecycler) { _, insets ->
            val insetsStatusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            adapterItemDecoration.topMarginFirstItem = searchbar.height + insetsStatusBar.top
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun MainViewModel.observeMain() {
        mains.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }

    private fun setFabListener() {
        fab.setOnClickListener { navToQuote() }
        fab.show()
    }

    override fun onPause() {
        super.onPause()
        fab.hide()
    }

    fun viewQuotesActionMode(quoteId: Int) {
        viewActionMode {
            object : BaseActionModeCallback {
                override var menuInflater = requireActivity().menuInflater
                override var menuId = R.menu.action_bar_edit

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem?): Boolean {
                    return when (item?.itemId) {
                        R.id.action_bar_edit -> {
                            navToQuote(quoteId)
                            mode.finish()
                            true
                        }

                        R.id.action_bar_delete -> {

                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun navToQuote(quoteId: Int = 0) {
        val direction = MainFragmentDirections.actionMainToQuote(quoteId)
        findNavController().navigate(direction)
    }

    private fun navToFilter() {
        findNavController().navigate(R.id.action_main_to_filter)
    }
}
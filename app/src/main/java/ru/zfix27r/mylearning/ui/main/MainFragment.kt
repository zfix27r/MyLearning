package ru.zfix27r.mylearning.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainBinding
import ru.zfix27r.mylearning.ui.ActionModeCallback
import ru.zfix27r.mylearning.ui.BaseFragment
import ru.zfix27r.mylearning.ui.main.quotes.MainQuotesAdapter
import ru.zfix27r.mylearning.ui.main.quotes.MainQuotesCallback
import ru.zfix27r.mylearning.ui.main.topics.MainTopicsAdapter
import ru.zfix27r.mylearning.ui.main.topics.MainTopicsCallback

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    private val quotesAdapter = MainQuotesAdapter {
        object : MainQuotesCallback {
            override fun onClick(quoteId: Int) {
                TODO("Not yet implemented")
            }

            override fun onLongClick(quoteId: Int) {
                viewQuotesActionMode(quoteId)
            }
        }
    }
    private val topicsAdapter = MainTopicsAdapter {
        object : MainTopicsCallback {
            override fun onClick(topicId: Int) {
                navToSelf(topicId)
            }

            override fun onLongClick(topicId: Int) {
                viewTopicsActionMode(topicId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attachViewModel()

        setToolbar()
        setView()

        viewModel.observeMain()
        viewModel.observeTopics()
        viewModel.observeQuotes()

        setListeners()
    }

    private fun setToolbar() {
        toolbar.updateMenu(R.menu.toolbar) {
            when (it.itemId) {
                R.id.toolbar_filter -> {
                    navToFilter()
                    true
                }

                R.id.toolbar_more -> {
                    navToMainMore()
                    true
                }

                else -> false
            }
        }
    }

    private fun setView() {
        binding.mainTopics.adapter = topicsAdapter
        binding.mainQuotes.adapter = quotesAdapter
    }

    private fun setListeners() {
        //binding.mainTopicsHeader.setOnClickListener { viewModel.expand() }
        /*
                binding.mainTopicsHeader.setOnLongClickListener {
                    navToMainMenu()
                    true
                }
        */
        binding.mainTopicsMore.setOnClickListener { navToMainMore() }
    }

    private fun MainViewModel.observeMain() {
        parent.observe(viewLifecycleOwner) { main ->
            binding.mainTopicsTitle.text = main?.title ?: getString(R.string.main_name)

            main?.subtitle?.let {
                binding.mainTopicsSubtitle.text = it
                binding.mainTopicsSubtitle.isVisible = true
            } ?: run {
                binding.mainTopicsSubtitle.isVisible = false
            }
        }
    }

    private fun MainViewModel.observeTopics() {
        topics.observe(viewLifecycleOwner) { topics ->
            //val span = 1
            //val staggeredLayoutManager = StaggeredGridLayoutManager(span, StaggeredGridLayoutManager.HORIZONTAL)
            //binding.mainTopics.layoutManager = staggeredLayoutManager
            topicsAdapter.submitList(topics)
        }
    }

    private fun MainViewModel.observeQuotes() {
        quotes.observe(viewLifecycleOwner) { quotesAdapter.submitList(it) }
    }

    fun viewTopicsActionMode(topicId: Int) {
        viewActionMode {
            object : ActionModeCallback {
                override var menuInflater = requireActivity().menuInflater
                override var menuId = R.menu.action_bar_edit

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem?): Boolean {
                    return when (item?.itemId) {
                        R.id.action_bar_edit -> {
                            navToTopicEditor(topicId)
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

    fun viewQuotesActionMode(quoteId: Int) {
        viewActionMode {
            object : ActionModeCallback {
                override var menuInflater = requireActivity().menuInflater
                override var menuId = R.menu.action_bar_edit

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem?): Boolean {
                    return when (item?.itemId) {
                        R.id.action_bar_edit -> {
                            navToQuoteEditor(quoteId)
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

    private fun navToSelf(topicId: Int) {
        val direction = MainFragmentDirections.actionMainSelf(topicId)
        findNavController().navigate(direction)
    }

    private fun navToTopicEditor(topicId: Int) {
        val direction = MainFragmentDirections.actionMainToTopicEditor(topicId)
        findNavController().navigate(direction)
    }

    private fun navToQuoteEditor(quoteId: Int) {
        val direction = MainFragmentDirections.actionMainToQuoteEditor(quoteId)
        findNavController().navigate(direction)
    }

    private fun navToMainMore() {
        findNavController().navigate(R.id.action_main_to_main_more)
    }

    private fun navToFilter() {

    }
}
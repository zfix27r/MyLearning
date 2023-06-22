package ru.zfix27r.mylearning.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainBinding
import ru.zfix27r.mylearning.ui.MainActivity
import ru.zfix27r.mylearning.ui.ToolbarMode
import ru.zfix27r.mylearning.ui.main.adapter.MainAdapter
import ru.zfix27r.mylearning.ui.main.adapter.MainAdapterCallback

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainAdapterCallback {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val args by navArgs<MainFragmentArgs>()

    private val mainActivity by lazy {
        activity as? MainActivity ?: throw ExceptionInInitializerError()
    }

    private val bottomNavView: BottomNavigationView by lazy { binding.mainBottomNavView }

    private val adapter = MainAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareToolbar()
        prepareView()
        observersUI()
    }

    private fun prepareView() {
        viewModel.loading(args.topicParentId)

        binding.mainDictionaryRecyclerView.adapter = adapter

        bottomNavView.setupWithNavController(findNavController())

        mainActivity.setOnClickLoadingRetry { viewModel.loading() }
    }

    private fun observersUI() {
        viewModel.data.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner) { mainActivity.viewLoading(it) }
        viewModel.isError.observe(viewLifecycleOwner) { mainActivity.viewError(it) }

        findNavController().currentBackStackEntry?.savedStateHandle?.let { saved ->
            saved.getLiveData<Boolean>(IS_DELETE).observe(viewLifecycleOwner) { isDelete ->
                saved.get<Int>(TOPIC_ID)?.let {
                    //if (isDelete) viewModel.delete(it)
                }
            }
        }
    }

    override fun viewAdapterItemMenu(topicId: Int) {
        val direction = MainFragmentDirections.actionMainToMainAdapterItemMenu(topicId)
        findNavController().navigate(direction)
    }

    override fun navigateToSelf(topicParentId: Int) {
        val direction = MainFragmentDirections.actionMainToSelf(topicParentId)
        findNavController().navigate(direction)
    }

    override fun navigateToTopicAdd() {
        findNavController().navigate(R.id.action_global_topic_editor)
    }

    private fun prepareToolbar() {
        mainActivity.toolbar(ToolbarMode.MAIN)
        mainActivity.toolbarHome()
    }

    companion object {
        const val TOPIC_ID = "topic_id"
        const val IS_DELETE = "is_delete"
    }
}
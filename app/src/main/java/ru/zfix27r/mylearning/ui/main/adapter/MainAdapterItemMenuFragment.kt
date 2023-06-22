package ru.zfix27r.mylearning.ui.main.adapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentMainAdapterItemMenuBinding
import ru.zfix27r.mylearning.ui.MainActivity
import ru.zfix27r.mylearning.ui.main.MainFragment

@AndroidEntryPoint
class MainAdapterItemMenuFragment :
    BottomSheetDialogFragment(R.layout.fragment_main_adapter_item_menu) {
    private val binding by viewBinding(FragmentMainAdapterItemMenuBinding::bind)
    private val viewModel by viewModels<MainAdapterItemModalViewModel>()
    private val args by navArgs<MainAdapterItemMenuFragmentArgs>()

    private val mActivity by lazy {
        activity as? MainActivity ?: throw ExceptionInInitializerError()
    }

    private fun backStackSavedState() =
        findNavController()
            .previousBackStackEntry
            ?.savedStateHandle
            ?: throw ExceptionInInitializerError()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareView()
        observersUI()
    }

    private fun prepareView() {
        viewModel.loading(args.topicId)
        binding.mainAdapterModalEdit.setOnClickListener { navigateToTopicEditor() }
        binding.mainAdapterModalDelete.setOnClickListener { showDeleteDialog() }
    }

    private fun observersUI() {
        viewModel.isError.observe(viewLifecycleOwner) { onError(it) }
    }

    private fun onError(t: Int?) {
        t?.let { mActivity.viewErrorInSnackBar(t) }
        findNavController().popBackStack()
    }

    private fun showDeleteDialog() {
        AlertDialog
            .Builder(requireContext())
            .setTitle(R.string.delete_name)
            .setMessage(R.string.main_adapter_item_menu_delete_message)
            .setPositiveButton(R.string.continue_action_name) { _, _ -> navigateBackForDelete() }
            .setNegativeButton(R.string.cancel_action_name) { _, _ -> navigateBack() }
            .create()
            .show()
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun navigateBackForDelete() {
        backStackSavedState()[MainFragment.TOPIC_ID] = viewModel.topicId
        backStackSavedState()[MainFragment.IS_DELETE] = true
        findNavController().popBackStack()
    }

    private fun navigateToTopicEditor() {
        val direction =
            MainAdapterItemMenuFragmentDirections.actionGlobalTopicEditor(viewModel.topicId)
        findNavController().navigate(direction)
    }
}
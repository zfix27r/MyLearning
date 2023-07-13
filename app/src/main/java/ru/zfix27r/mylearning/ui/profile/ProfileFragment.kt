package ru.zfix27r.mylearning.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentProfileBinding
import ru.zfix27r.mylearning.ui.base.BaseDialogFragment

@AndroidEntryPoint
class ProfileFragment : BaseDialogFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInsets()
        setWidth()

        binding.profileMode.setOnClickListener {
            viewModel.changeThemeMode()
        }

        binding.profileSettings.setOnClickListener {
            navToSettings()
        }
    }

    private fun navToSettings() {

    }
}
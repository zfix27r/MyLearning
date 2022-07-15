package ru.sergeyzabelin.mylearning.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentMainBinding
import ru.sergeyzabelin.mylearning.utils.autoCleared

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    var binding by autoCleared<FragmentMainBinding>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentMainBinding.inflate(
            inflater, container, false
        )
        binding = dataBinding

        // TODO обновление бд при первом запуске
        viewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDictionary.setOnClickListener {
            if (viewModel.isSimpleMode())
                findNavController().navigate(R.id.action_mainFragment_to_dictionaryFragment)
            else
                findNavController().navigate(R.id.action_mainFragment_to_dictionaryExtendedFragment)
        }
    }
}
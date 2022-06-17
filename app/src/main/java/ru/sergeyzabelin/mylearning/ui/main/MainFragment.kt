package ru.sergeyzabelin.mylearning.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentMainBinding
import ru.sergeyzabelin.mylearning.di.Injectable
import ru.sergeyzabelin.mylearning.utils.AppExecutors
import ru.sergeyzabelin.mylearning.utils.autoCleared
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

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
            findNavController().navigate(R.id.action_navMainFragment_to_navDictionaryFragment)
        }
    }
}
package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryDetailBinding


class DictionaryDetailFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryDetailBinding

    private val args: DictionaryDetailFragmentArgs by navArgs()
    private val viewModel: DictionaryDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        viewModel.dictionaryDetailId = args.dictionaryId
        viewModel.getDictionaryById()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dictionaryDetail.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
        }
    }
}
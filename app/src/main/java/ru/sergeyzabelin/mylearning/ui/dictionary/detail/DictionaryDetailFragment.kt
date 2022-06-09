package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryDetailBinding


class DictionaryDetailFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryDetailBinding
    private lateinit var adapter: ArticleAdapter

    private val args by navArgs<DictionaryDetailFragmentArgs>()
    private val viewModel by viewModels<DictionaryDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false)


        viewModel.topicId = args.topicId
        viewModel.getTopicWithArticlesById()


        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ArticleAdapter { url -> onClickLoadPageByUrl(url) }
        binding.dictionaryDetailLinkRecycler.adapter = adapter

        viewModel.topicWithArticles.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel
            val toolbar = requireActivity().findViewById<Toolbar>(R.id.topAppbar)
            toolbar.title = it.topic.title
            toolbar.subtitle = it.topic.label


            adapter.submitList(it.articles)
        }
    }

    private fun onClickLoadPageByUrl(url: String) {

    }
}
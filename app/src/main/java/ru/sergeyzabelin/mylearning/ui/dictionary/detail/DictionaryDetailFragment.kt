package ru.sergeyzabelin.mylearning.ui.dictionary.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryDetailBinding


class DictionaryDetailFragment : Fragment() {
    private lateinit var binding: FragmentDictionaryDetailBinding
    private lateinit var adapter: ArticleAdapter
    private lateinit var toolbar: Toolbar

    private val args by navArgs<DictionaryDetailFragmentArgs>()
    private val viewModel by viewModels<DictionaryDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.topicId = args.topicId

        toolbar = requireActivity().findViewById(R.id.topAppbar)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDictionaryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter =
            ArticleAdapter(
                { url -> onClickLoadPageByUrl(url) },
                { article -> onRefreshDescription(article) })
        binding.dictionaryDetailLinkRecycler.adapter = adapter

        binding.viewModel = viewModel


        viewModel.topicWithArticles.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.e("asd", it.toString())

                binding.viewModel = viewModel

                toolbar.title = it.topic.title
                toolbar.subtitle = it.topic.label

                //adapter.submitList(it.articles)
            }
        }
    }


    override fun onPause() {
        super.onPause()

        toolbar.subtitle = null
    }

    private fun onClickLoadPageByUrl(url: String) {

    }

    private fun onRefreshDescription(article: Article) {
        viewModel.refreshDescription(article)
    }
}
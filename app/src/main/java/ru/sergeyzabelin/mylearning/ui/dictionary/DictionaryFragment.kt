package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.data.model.db.Topic
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.ui.common.RetryCallback
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private var binding by autoCleared<FragmentDictionaryBinding>()
    private var topicAdapter by autoCleared<DictionaryTopicAdapter>()
    private var articleAdapter by autoCleared<DictionaryArticleAdapter>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentDictionaryBinding.inflate(inflater, container, false)

        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                Log.e("retry", "on")
            }
        }

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dictionaryNavigationBar.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menuDictionaryTopic -> {
                    binding.dictionaryRecyclerArticle.visibility = View.GONE
                    binding.dictionaryRecyclerTopic.visibility = View.VISIBLE

                    true
                }
                R.id.menuDictionaryArticle -> {
                    binding.dictionaryRecyclerTopic.visibility = View.GONE
                    binding.dictionaryRecyclerArticle.visibility = View.VISIBLE

                    true
                }
                else -> false
            }

        }


        binding.lifecycleOwner = viewLifecycleOwner
        //binding.topics = viewModel.topics


        topicAdapter = DictionaryTopicAdapter { topic -> onClickGoNext(topic) }
        binding.dictionaryRecyclerTopic.adapter = topicAdapter

        articleAdapter = DictionaryArticleAdapter { article -> onClickGoWeb(article)}
        binding.dictionaryRecyclerArticle.adapter = articleAdapter

        viewModel.data.observe(viewLifecycleOwner) { list ->

            list.data?.let { data ->
                Log.e("s", list.data.toString())

                topicAdapter.submitList(data.topics)
                articleAdapter.submitList(data.articles)

                viewArticleBadge(data.articles.size)
            }
        }
    }

    private fun onClickGoNext(topic: Topic) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionNavDictionaryFragmentSelf(topic.id))
    }


    private fun onClickGoWeb(article: Article) {
    }

    private fun viewArticleBadge(size: Int) {
        val badge = binding.dictionaryNavigationBar.getOrCreateBadge(R.id.menuDictionaryArticle)
        badge.isVisible = true
        badge.number = size
    }

    fun onClickFab() {
        findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)

        //menu.findItem(R.id.action_done).isVisible = false
    }


}
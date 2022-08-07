package ru.sergeyzabelin.mylearning.ui.content

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentContentBinding
import ru.sergeyzabelin.mylearning.ui.content.question.QuestionFragment
import ru.sergeyzabelin.mylearning.ui.content.quote.QuoteFragment
import ru.sergeyzabelin.mylearning.ui.dictionary.DictionaryFragmentDirections
import ru.sergeyzabelin.mylearning.utils.autoCleared

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private val viewModel by viewModels<ContentViewModel>()
    private var binding by autoCleared<FragmentContentBinding>()
    private var adapter by autoCleared<ContentPagerAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentContentBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionBar()
        adapter()
        dataObserver()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.content_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.add -> findNavController().navigate(
                        DictionaryFragmentDirections
                            .actionDictionaryToTopicEditor(0, viewModel.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        adapter = ContentPagerAdapter(this)

        adapter.addFragment(QuoteFragment(viewModel), R.drawable.ic_baseline_format_quote_24)
        adapter.addFragment(QuestionFragment(viewModel), R.drawable.ic_baseline_question_answer_24)

        binding.pager.adapter = adapter
        binding.pager.currentItem = 0

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.setIcon(adapter.getTabIcon(position))
        }.attach()
    }

    private fun dataObserver() {
        viewModel.content.observe(viewLifecycleOwner) { content ->
            content.data?.let { data ->
                setToolbarTitles(data.topic)
            }
        }
    }

    private fun setToolbarTitles(topic: Topic) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = topic.title
            actionBar.subtitle = topic.subTitle
        }
    }
}
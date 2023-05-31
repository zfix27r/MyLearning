package ru.sergeyzabelin.zfix27r.ui.content

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.zfix27r.R
import ru.sergeyzabelin.zfix27r.databinding.FragmentContentBinding
import ru.sergeyzabelin.zfix27r.ui.content.question.QuestionFragment
import ru.sergeyzabelin.zfix27r.ui.content.quote.QuoteFragment

@AndroidEntryPoint
class ContentFragment : BaseFragment() {

    private val viewModel by viewModels<ContentViewModel>()
    private val binding by viewBinding(FragmentContentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        actionBar()
        adapter()
        dataObserver()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        with(menuHost) {
            addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.content_app_bar, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.add -> {}//findNavController().navigate(
                            //DictionaryFragmentDirections
                                //.actionDictionaryToTopicEditor(0, viewModel.topicId)
                        //)
                        android.R.id.home -> findNavController().popBackStack()
                    }

                    return true
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    private fun adapter() {
        val adapter = ContentPagerAdapter(this)

        adapter.addFragment(QuoteFragment(viewModel), R.drawable.ic_baseline_format_quote_24)
        adapter.addFragment(QuestionFragment(viewModel), R.drawable.ic_baseline_question_answer_24)

        binding.pager.adapter = adapter
        binding.pager.currentItem = 0

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.setIcon(adapter.getTabIcon(position))
        }.attach()
    }

    private fun dataObserver() {
        viewModel.content.observe(viewLifecycleOwner) {
            setToolbarTitles(it.topic.title, it.topic.subtitle)
        }
    }
}
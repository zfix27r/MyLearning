package ru.sergeyzabelin.mylearning.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentContentBinding
import ru.sergeyzabelin.mylearning.ui.content.quote.QuoteFragment
import ru.sergeyzabelin.mylearning.utils.autoCleared

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private val viewModel by viewModels<ContentViewModel>()
    private var binding by autoCleared<FragmentContentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentContentBinding.inflate(inflater, container, false)

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ContentPagerAdapter(requireActivity())


        viewModel.data.observe(viewLifecycleOwner) { content ->
            content.data?.let { data ->
                (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
                    actionBar.title = data.topic.title
                    actionBar.subtitle = data.topic.subTitle
                }

                if (data.quotes.isNotEmpty()) {
                    adapter.addFragment(
                        QuoteFragment(viewModel),
                        R.drawable.ic_baseline_format_quote_24
                    )
                }

                //adapter.addFragment(SourceFragment(), R.drawable.ic_baseline_source_24)
                //adapter.addFragment(QuestionFragment(), R.drawable.ic_baseline_question_answer_24)

                binding.pager.adapter = adapter
                binding.pager.currentItem = 0
                TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
                    tab.setIcon(adapter.getTabIcon(position))
                }.attach()

            }
        }
    }
}
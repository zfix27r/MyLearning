package ru.sergeyzabelin.mylearning.ui.content.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sergeyzabelin.mylearning.data.model.db.Quote
import ru.sergeyzabelin.mylearning.databinding.FragmentQuoteBinding
import ru.sergeyzabelin.mylearning.ui.content.ContentViewModel
import ru.sergeyzabelin.mylearning.utils.autoCleared

class QuoteFragment(private val viewModel: ContentViewModel) : Fragment() {

    private var binding by autoCleared<FragmentQuoteBinding>()
    private var adapter by autoCleared<QuoteAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentQuoteBinding.inflate(inflater, container, false)

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = QuoteAdapter { quote -> onLongClickActionMode(quote) }
        binding.recycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { content ->
            content.data?.let {
                adapter.submitList(it.quotes)
            }
        }
    }

    private fun onLongClickActionMode(quote: Quote) {

    }
}
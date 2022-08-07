package ru.sergeyzabelin.mylearning.ui.content.quote

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.data.model.db.Quote
import ru.zfix27r.data.model.db.Source
import ru.sergeyzabelin.mylearning.databinding.FragmentQuoteBinding
import ru.sergeyzabelin.mylearning.ui.content.ContentFragmentDirections
import ru.sergeyzabelin.mylearning.ui.content.ContentViewModel
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class QuoteFragment(private val viewModel: ContentViewModel) : Fragment() {

    private var binding by autoCleared<FragmentQuoteBinding>()
    private var adapter by autoCleared<QuoteAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentQuoteBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuoteAdapter(object : QuoteActionListener {
            override fun onUrlOpen(source: Source) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(source.url))
                startActivity(browserIntent)
            }

            override fun onQuoteAdd(quote: Quote) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToQuoteEditor(quote.id)
                )
            }

            override fun onSourceAdd(source: Source) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToSourceEditor(source.id)
                )
            }

            override fun onQuoteEdit(quote: Quote) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToQuoteEditor(quote.id)
                )
            }

            override fun onSourceEdit(source: Source) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToSourceEditor(source.id)
                )
            }

            override fun onQuoteDelete(quote: Quote) {

            }

            override fun onSourceDelete(source: Source) {

            }
        })
        binding.recycler.adapter = adapter

        viewModel.content.observe(viewLifecycleOwner) { content ->
            content.data?.let {
                adapter.submitList(it.quotes)
            }
        }
    }

}
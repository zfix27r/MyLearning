package ru.sergeyzabelin.zfix27r.ui.content.quote

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.zfix27r.R
import ru.sergeyzabelin.zfix27r.databinding.FragmentQuoteBinding
import ru.sergeyzabelin.zfix27r.ui.content.ContentViewModel
import ru.zfix27r.domain.model.content.ContentDataModel

typealias QuoteWithSource = ContentDataModel.QuoteWithSource
typealias Quote = ContentDataModel.QuoteWithSource.Quote
typealias Source = ContentDataModel.QuoteWithSource.Source

@AndroidEntryPoint
class QuoteFragment(private val viewModel: ContentViewModel) : Fragment() {

    private val binding by viewBinding(FragmentQuoteBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = QuoteAdapter(onListenActionFromAdapter(), onListenContextMenuFromAdapter())
        binding.recycler.adapter = adapter

        viewModel.content.observe(viewLifecycleOwner) {
            adapter.submitList(it.quotes)
        }
    }

    private fun onListenActionFromAdapter(): QuoteActionListener {
        return object : QuoteActionListener {
            override fun onUrlOpen(source: Source) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(source.url))
                startActivity(browserIntent)
            }
        }
    }

    private fun onListenContextMenuFromAdapter(): View.OnCreateContextMenuListener {
        return View.OnCreateContextMenuListener { menu, v, _ ->
            menu?.let {
                v?.let {
                    val quote = v.tag as QuoteWithSource

                    val quoteEdit = menu.add(0, v.id, 0, getString(R.string.edit))
                    val quoteDelete = menu.add(0, v.id, 0, getString(R.string.delete))
                    val source = menu.addSubMenu(1, v.id, 0, R.string.source)
                    val sourceEdit = source.add(1, v.id, 0, getString(R.string.edit))
                    val sourceDelete = source.add(1, v.id, 0, getString(R.string.delete))
                }
            }
        }
    }

}

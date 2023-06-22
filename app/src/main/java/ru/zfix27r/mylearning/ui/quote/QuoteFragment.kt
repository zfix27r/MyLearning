package ru.zfix27r.mylearning.ui.quote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.FragmentQuoteBinding

@AndroidEntryPoint
class QuoteFragment : Fragment(R.layout.fragment_quote) {
    private val binding by viewBinding(FragmentQuoteBinding::bind)
    private val viewModel by viewModels<QuoteViewModel>()

    private val callback = object : QuoteAdapterCallback {
        override fun openUrl(url: String) {
            TODO("Not yet implemented")
        }
    }

    private val adapter = QuoteAdapter(callback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quoteRecyclerView.adapter = adapter
    }


/*    private fun onListenContextMenuFromAdapter(): View.OnCreateContextMenuListener {
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
    }*/

}

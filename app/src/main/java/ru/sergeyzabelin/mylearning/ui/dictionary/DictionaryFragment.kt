package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.data.model.db.Article
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.ui.common.RetryCallback
import ru.sergeyzabelin.mylearning.utils.autoCleared


@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private var binding by autoCleared<FragmentDictionaryBinding>()
    private var adapter by autoCleared<DictionaryAdapter>()


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

        binding.lifecycleOwner = viewLifecycleOwner

        adapter =
            DictionaryAdapter({ id -> onClickGoNext(id) }, { id -> onLongClickActionMode(id) })
        binding.dictionaryRecycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { list ->

            list.data?.let { data ->
                (activity as AppCompatActivity).supportActionBar?.let {
                    it.title = data.topic.title
                    it.subtitle = data.topic.label
                }

                Log.e("s", list.data.toString())

                adapter.submitList(data.topics)
            }
        }
    }

    private fun onClickGoNext(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionDictionaryFragmentSelf(id)
        )
    }


    private fun onClickGoWeb(article: Article) {

    }

    private fun onLongClickActionMode(id: Long) {
        val callback = object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                activity?.menuInflater?.inflate(R.menu.dictionary_contextual_action_bar, menu)
                menu?.findItem(R.id.done)?.isVisible = false
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {

                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.edit -> {
                        mode?.finish()
                        findNavController().navigate(
                            DictionaryFragmentDirections
                                .actionDictionaryFragmentToDictionaryTopicEditFragment(id)
                        )
                        true
                    }
                    R.id.delete -> {
                        // Handle more item (inside overflow menu) press
                        true
                    }
                    R.id.done -> {
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }
        }

        val actionMode = activity?.startActionMode(callback)
        //actionMode?.title = "1 selected"
    }

/*    private fun viewArticleBadge(size: Int) {
        val badge = binding.dictionaryNavigationBar.getOrCreateBadge(R.id.menuDictionaryArticle)
        badge.isVisible = true
        badge.number = size
    }*/

    fun onClickFab() {
        //findNavController().navigate(R.id.act)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_dictionary, menu)
    }

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dictionaryAdd -> {
*//*                if (binding.dictionaryNavigationBar.selectedItemId == R.id.menuDictionaryTopic) {
*//**//*                    findNavController().navigate(
                        DictionaryFragmentDirections.actionNavDictionaryFragmentToNavDictionaryAddFragment(
                            selectedTopicId = viewModel.selectedTopicId
                        )
                    )*//**//*
                } else {

                }*//*

                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }*/
}

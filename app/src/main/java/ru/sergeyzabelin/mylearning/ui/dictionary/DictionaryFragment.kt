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
import ru.sergeyzabelin.mylearning.data.model.db.Topic
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

        binding.fab.setOnClickListener {
            findNavController().navigate(
                DictionaryFragmentDirections.actionDictionaryFragmentToDictionaryTopicAddFragment(
                    viewModel.savedTopicId
                )
            )
        }

        binding.lifecycleOwner = viewLifecycleOwner

        adapter =
            DictionaryAdapter(
                { id -> onClickGoNextTopic(id) },
                { id -> onClickGoTopicQuote(id) },
                { topic -> onLongClickActionMode(topic) })
        binding.dictionaryRecycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { list ->

            list.data?.let { data ->
                (activity as AppCompatActivity).supportActionBar?.let {
                    it.title = data.topic.title
                    it.subtitle = data.topic.subTitle
                }

                Log.e("s", list.data.toString())

                adapter.submitList(data.topics)
            }
        }
    }

    private fun onClickGoNextTopic(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionDictionaryFragmentSelf(id)
        )
    }

    private fun onClickGoTopicQuote(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionDictionaryFragmentToQuoteFragment(id)
        )
    }

    private fun onLongClickActionMode(topic: Topic) {
        val callback = object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                activity?.menuInflater?.inflate(R.menu.dictionary_contextual_action_bar, menu)
                menu?.let {
                    it.findItem(R.id.done)?.isVisible = false
                    if (topic.isHasChild) it.findItem(R.id.delete)?.isVisible = false
                }
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
                                .actionDictionaryFragmentToDictionaryTopicEditFragment(topic.id)
                        )
                        true
                    }
                    R.id.delete -> {
                        mode?.let {
                            it.title = getString(R.string.message_delete_order_confirmation)
                            it.menu.findItem(R.id.edit).isVisible = false
                            it.menu.findItem(R.id.delete).isVisible = false
                            it.menu.findItem(R.id.done).isVisible = true
                        }
                        true
                    }
                    R.id.done -> {
                        viewModel.topicDelete(topic)
                        mode?.finish()
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

    //private fun topicEdit

/*    private fun viewArticleBadge(size: Int) {
        val badge = binding.dictionaryNavigationBar.getOrCreateBadge(R.id.menuDictionaryArticle)
        badge.isVisible = true
        badge.number = size
    }*/

    fun onClickFab() {
        //findNavController().navigate(R.id.act)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_app_bar, menu)
    }
}

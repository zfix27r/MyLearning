package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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

        actionBar()
        adapter()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dictionary_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        adapter = DictionaryAdapter(
            { id -> navSelf(id) },
            { id -> navContent(id) },
            { topic -> onLongClickActionMode(topic) })

        binding.recycler.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { list ->
            list.data?.let { data ->
                (activity as AppCompatActivity).supportActionBar?.let {
                    it.title = data.topic.title
                    it.subtitle = data.topic.subTitle
                }

                adapter.submitList(data.topics)
            }
        }
    }

    private fun navSelf(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionDictionaryFragmentSelf(id)
        )
    }

    private fun navContent(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections.actionDictionaryFragmentToContentFragment(id)
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
                    R.id.add -> {
                        mode?.finish()
                        navTopicAdd(topic.id)
                        true
                    }
                    R.id.edit -> {
                        mode?.finish()
                        navTopicEdit(topic.id)
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

        activity?.startActionMode(callback)
    }

    private fun navTopicAdd(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections
                .actionDictionaryFragmentToTopicAddFragment(id)
        )
    }

    private fun navTopicEdit(id: Long) {
        findNavController().navigate(
            DictionaryFragmentDirections
                .actionDictionaryFragmentToTopicEditFragment(id)
        )
    }
}

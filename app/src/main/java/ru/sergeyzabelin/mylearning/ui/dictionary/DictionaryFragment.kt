package ru.sergeyzabelin.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.sergeyzabelin.mylearning.R
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.zfix27r.domain.model.DictionaryResModel
import ru.zfix27r.domain.model.common.ResponseType


typealias TopicMain = DictionaryResModel.Data.TopicMain
typealias TopicSub = DictionaryResModel.Data.TopicSub

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel by viewModels<DictionaryViewModel>()
    private val binding by viewBinding(FragmentDictionaryBinding::bind)
    private val args by navArgs<DictionaryFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadTopic()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dictionary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        actionBar()
        adapter()
        //observeResponseResult()
    }

    private fun actionBar() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.dictionary_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.add -> findNavController().navigate(
                        DictionaryFragmentDirections
                            .actionDictionaryToTopicEditor(parentId = args.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun adapter() {
        val adapter = DictionaryAdapter(onActionListenersAdapter(), onCreateAdapterContextMenu())

        binding.recycler.adapter = adapter
        registerForContextMenu(binding.recycler)

        viewModel.topic.observe(viewLifecycleOwner) {
            setToolbarTitles(it.topic)
            if (it.topics.isEmpty()) {
                binding.recycler.visibility = View.GONE
                binding.noResult.visibility = View.VISIBLE
            } else adapter.submitList(it.topics)
        }
    }

    private fun onActionListenersAdapter(): DictionaryActionListener {
        return object : DictionaryActionListener {
            override fun onSelf(topicId: Long) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToDictionary(topicId = topicId)
                )
            }

            override fun onDetails(topicId: Long) {
                findNavController().navigate(
                    DictionaryFragmentDirections.actionDictionaryToContent(topicId = topicId)
                )
            }
        }
    }

    private fun onCreateAdapterContextMenu(): View.OnCreateContextMenuListener {
        return View.OnCreateContextMenuListener { menu, v, _ ->
            menu?.let {
                v?.let {
                    val topic = v.tag as TopicSub

                    val edit = menu.add(0, v.id, 0, getString(R.string.edit))
                    val delete = menu.add(0, v.id, 0, getString(R.string.delete))

                    edit.setOnMenuItemClickListener {
                        findNavController().navigate(
                            DictionaryFragmentDirections
                                .actionDictionaryToTopicEditor(topicId = topic.id)
                        )
                        return@setOnMenuItemClickListener true
                    }

                    delete.setOnMenuItemClickListener {
                        onDeleteTopic(v)
                        return@setOnMenuItemClickListener true
                    }
                }
            }
        }
    }

    private fun onDeleteTopic(v: View) {
        val topic = v.tag as TopicSub
        val snackBar = Snackbar.make(v, getString(R.string.delete_confirmed), Snackbar.LENGTH_LONG)
        snackBar.setAction(getString(R.string.undo)) { snackBar.dismiss() }
        snackBar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                if (event == DISMISS_EVENT_TIMEOUT) viewModel.delete(topic.id)
            }
        })
        snackBar.show()
    }

    private fun setToolbarTitles(topic: TopicMain) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = topic.title
            actionBar.subtitle = topic.subTitle
        }
    }

    private fun observeResponseResult() {
        viewModel.result.observe(viewLifecycleOwner) {
            when (it.type) {
                ResponseType.SUCCESS -> {
                    findNavController().popBackStack()
                }
                ResponseType.UNKNOWN_ERROR -> notifyMessageOnUI(getString(R.string.error_unknown))
                else -> {}
            }
        }
    }

    private fun notifyMessageOnUI(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
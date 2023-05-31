package ru.zfix27r.mylearning.ui.dictionary

import android.os.Bundle
import android.view.*
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
import ru.zfix27r.mylearning.databinding.FragmentDictionaryBinding

@AndroidEntryPoint
class DictionaryFragment : Fragment() {
    private val binding by viewBinding(FragmentDictionaryBinding::bind)
    private val viewModel by viewModels<DictionaryViewModel>()
    private val adapter = DictionaryAdapter(onListenAction(), onListenContextMenu())
    private val args by navArgs<DictionaryFragmentArgs>()

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
        binding.recycler.adapter = adapter

        setActionBar()
        setTopicObserver()
        setTopicsObserver()
        setResponseObserver()
    }

    private fun setActionBar() {
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

    private fun setTopicObserver() {
        viewModel.topic.observe(viewLifecycleOwner) {
            setToolbarTitles(it.title, it.subTitle)
        }
    }

    private fun setTopicsObserver() {
        viewModel.topics.observe(viewLifecycleOwner) {
            hideResponseMessage()
            adapter.submitList(it)
        }
    }

    private fun setResponseObserver() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                ResponseType.RESPONSE_EMPTY -> showResponseMessage()
                ResponseType.SUCCESS -> findNavController().popBackStack()
                ResponseType.UNKNOWN_ERROR -> notifyMessageOnUI(getString(R.string.error_unknown))
                else -> {}
            }
        }
    }

    private fun onListenAction(): DictionaryActionListener {
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

    private fun onListenContextMenu(): View.OnCreateContextMenuListener {
        return View.OnCreateContextMenuListener { menu, v, _ ->
            menu?.let {
                v?.let {
                    val topic = v.tag as Topics

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
        val topic = v.tag as Topics
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

    private fun showResponseMessage() {
        binding.empty.visibility = View.VISIBLE
    }

    private fun hideResponseMessage() {
        binding.empty.visibility = View.GONE
    }

    private fun notifyMessageOnUI(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
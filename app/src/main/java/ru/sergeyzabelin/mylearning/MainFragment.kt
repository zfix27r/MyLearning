package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.sergeyzabelin.mylearning.domain.MainViewModel
import ru.sergeyzabelin.mylearning.adapters.LessonTopicAdapter
import ru.sergeyzabelin.mylearning.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by activityViewModels()

    private var isEditing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )

        binding.lessonFab.setOnClickListener {
            findNavController().navigate(R.id.action_navMainFragment_to_navLessonTopicAddFragment)
        }

        val lessonTopicAdapter = LessonTopicAdapter(activity)
        binding.lessonRv.adapter = lessonTopicAdapter
        viewModel
        //Log.e("ds", viewModel.getLessonTopicGroup().toString())
        //lessonTopicAdapter.submitList(viewModel.getLessonTopicGroup())

        return binding.root
    }

/*    fun updateOptionsMenu() {
        isEditing = !isEditing
        requireActivity().invalidateOptionsMenu()
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_done)
        item.isVisible = isEditing
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                // navigate to settings screen
                true
            }
            R.id.action_done -> {
                // save profile changes
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
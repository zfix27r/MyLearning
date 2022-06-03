package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import ru.sergeyzabelin.mylearning.adapters.LessonAdapter
import ru.sergeyzabelin.mylearning.databinding.FragmentMainBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel

class MainFragment : Fragment(),
    LessonAdapter.OnLessonTopicSelectedListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: LessonAdapter

    private val viewModel: MainViewModel by activityViewModels()

    private var isEditing: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lessonFab.setOnClickListener {
            findNavController().navigate(R.id.action_navMainFragment_to_navLessonTopicAddFragment)
        }

        adapter = object : LessonAdapter(viewModel.getLesson(), this@MainFragment) {
            override fun onDataChanged() {
                if (itemCount == 0) {
                    binding.lessonRv.visibility = View.GONE
                    binding.lessonEmpty.visibility = View.VISIBLE
                } else {
                    binding.lessonRv.visibility = View.VISIBLE
                    binding.lessonEmpty.visibility = View.GONE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                Snackbar.make(
                    binding.root,
                    "Error: check logs for info.", Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.lessonRv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_done)
        item.isVisible = isEditing
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_dictionary -> {
                findNavController().navigate(R.id.action_global_navDictionaryFragment)
                true
            }
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

    override fun onLessonTopicSelected(document: DocumentSnapshot) {
        val action =
            MainFragmentDirections.actionNavMainFragmentToNavLessonDetailFragment(document.id)

        findNavController().navigate(action)
    }

    override fun onStart() {
        super.onStart()

        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
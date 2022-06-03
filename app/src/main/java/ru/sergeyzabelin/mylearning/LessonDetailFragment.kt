package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import ru.sergeyzabelin.mylearning.adapters.LessonDetailAdapter
import ru.sergeyzabelin.mylearning.databinding.FragmentLessonDetailBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel

class LessonDetailFragment : Fragment(), LessonDetailAdapter.OnDetailSelectedListener {

    private lateinit var binding: FragmentLessonDetailBinding
    private lateinit var adapter: LessonDetailAdapter

    private val viewModel: MainViewModel by activityViewModels()
    private val safeArgs: LessonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentLessonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("sad", safeArgs.lessonId)

        adapter =
            object : LessonDetailAdapter(viewModel.getLessonDetail(), this@LessonDetailFragment) {
                override fun onDataChanged() {
                    if (itemCount == 0) {
                        binding.lessonDetailRv.visibility = View.GONE
                        binding.lessonDetailEmpty.visibility = View.VISIBLE
                    } else {
                        binding.lessonDetailRv.visibility = View.VISIBLE
                        binding.lessonDetailEmpty.visibility = View.GONE
                    }
                }

                override fun onError(e: FirebaseFirestoreException) {
                    Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
                }
            }

    }

    override fun onDetailSelected(document: DocumentSnapshot) {

    }
}
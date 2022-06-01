package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import ru.sergeyzabelin.mylearning.domain.MainViewModel
import ru.sergeyzabelin.mylearning.adapters.LessonTopicAdapter
import ru.sergeyzabelin.mylearning.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        val lessonTopicAdapter = LessonTopicAdapter(activity)
        binding.mainLessonRv.adapter = lessonTopicAdapter
        viewModel
        //Log.e("ds", viewModel.getLessonTopicGroup().toString())
        //lessonTopicAdapter.submitList(viewModel.getLessonTopicGroup())

        return binding.root
    }
}
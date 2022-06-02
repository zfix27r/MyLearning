package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import ru.sergeyzabelin.mylearning.databinding.FragmentLessonTopicAddBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel


class LessonTopicAddFragment : Fragment() {
    private lateinit var binding: FragmentLessonTopicAddBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_topic_add, container, false)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {

                true
            }
            R.id.action_done -> {
                checkAllInputAndDone()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkAllInputAndDone() {
        val isInputLessonTitleCorrect =
            isInputCorrect(binding.lessonTopicAddMetTitle.text.toString())
        val isInputLessonDescriptionCorrect =
            isInputCorrect(binding.lessonTopicAddMetDescription.text.toString())
        val isInputLessonResourceLinkCorrect =
            isInputCorrect(binding.lessonTopicAddMetResourceLink.text.toString())

        if (isInputLessonTitleCorrect &&
            isInputLessonDescriptionCorrect &&
            isInputLessonResourceLinkCorrect
        ) {
            viewModel.setLessonTopicToFirestore()
        } else {
            Toast.makeText(
                requireContext(),
                requireActivity().resources.getString(R.string.lesson_topic_add_error_field_empty),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isInputCorrect(inputString: String): Boolean {
        if (inputString.isNotEmpty()) {
            return true
        }

        return false
    }
}
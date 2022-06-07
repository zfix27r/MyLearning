package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.sergeyzabelin.mylearning.data.entities.Lesson
import ru.sergeyzabelin.mylearning.databinding.FragmentLessonAddBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel

class LessonTopicAddFragment : Fragment() {
    private lateinit var binding: FragmentLessonAddBinding

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
            FragmentLessonAddBinding.inflate(inflater, container, false)

/*        viewModel.getException()?.observe(viewLifecycleOwner) { finalData ->
            when (finalData.status) {
                Resource.Status.SUCCESS -> {
                    viewMessageUI(R.string.lesson_topic_add_exception_done)
                    findNavController().popBackStack()
                }
                Resource.Status.LOADING -> {}
                Resource.Status.ERROR -> viewMessageUI(R.string.lesson_topic_add_exception_no_internet)
            }
        }*/

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
            isInputCorrect(binding.lessonAddTitle.text.toString())
        val isInputLessonDescriptionCorrect =
            isInputCorrect(binding.lessonAddDescription.text.toString())

        if (isInputLessonTitleCorrect &&
            isInputLessonDescriptionCorrect
        ) {
            val lesson = Lesson(
                binding.lessonAddTitle.text.toString(),
                binding.lessonAddDescription.text.toString()
            )

            //viewModel.setLesson(lesson)
        } else {
            viewMessageUI(R.string.lesson_topic_add_exception_field_empty)
        }
    }

    private fun isInputCorrect(inputString: String): Boolean {
        if (inputString.isNotEmpty()) {
            return true
        }

        return false
    }

    private fun viewMessageUI(resourceStringId: Int) {
        Toast.makeText(
            requireContext(),
            requireActivity().resources.getString(resourceStringId),
            Toast.LENGTH_SHORT
        ).show()
    }
}
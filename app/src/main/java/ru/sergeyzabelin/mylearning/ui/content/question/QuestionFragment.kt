package ru.sergeyzabelin.mylearning.ui.content.question

/*

class QuestionFragment(private val viewModel: ContentViewModel) : Fragment() {

    private var binding by autoCleared<FragmentQuestionBinding>()
    private var adapter by autoCleared<QuestionAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentQuestionBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuestionAdapter(object : QuestionActionListener {
            override fun onAdd(question: QuestionDbEntity) {
            }

            override fun onEdit(question: QuestionDbEntity) {
            }

            override fun onDelete(question: QuestionDbEntity) {
            }
        })

        binding.recycler.adapter = adapter
        viewModel.content.observe(viewLifecycleOwner) { content ->
*/
/*            content.data?.let {
                adapter.submitList(it.questions)
            }*//*

        }
    }

}*/

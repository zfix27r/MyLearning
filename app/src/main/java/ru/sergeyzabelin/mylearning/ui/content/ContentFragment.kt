package ru.sergeyzabelin.mylearning.ui.content

/*
@AndroidEntryPoint
class ContentFragment : Fragment() {

    private val viewModel by viewModels<ContentViewModel>()
    private var binding by autoCleared<FragmentContentBinding>()
    private var adapter by autoCleared<ContentPagerAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentContentBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar()
        adapter()
        dataObserver()
    }

    private fun actionBar() {
*/
/*        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.content_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.add -> findNavController().navigate(
                        DictionaryFragmentDirections
                            .actionDictionaryToTopicEditor(0, viewModel.topicId)
                    )
                    android.R.id.home -> findNavController().popBackStack()
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)*//*

    }

    private fun adapter() {
        adapter = ContentPagerAdapter(this)

        adapter.addFragment(QuoteFragment(viewModel), R.drawable.ic_baseline_format_quote_24)
        adapter.addFragment(QuestionFragment(viewModel), R.drawable.ic_baseline_question_answer_24)

        binding.pager.adapter = adapter
        binding.pager.currentItem = 0

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.setIcon(adapter.getTabIcon(position))
        }.attach()
    }

    private fun dataObserver() {
        viewModel.content.observe(viewLifecycleOwner) { content ->
            when (content) {
                is ContentResModel.Success -> {
                    setToolbarTitles(content.topic)
                }
                is ContentResModel.Fail -> {}
            }
        }
    }

    private fun setToolbarTitles(topic: ContentTopicModel) {
        (activity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = topic.title
            actionBar.subtitle = topic.subTitle
        }
    }
}*/

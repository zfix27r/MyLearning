package ru.sergeyzabelin.mylearning.ui.content.quote

/*
@AndroidEntryPoint
class QuoteFragment(private val viewModel: ContentViewModel) : Fragment() {

    private var binding by autoCleared<FragmentQuoteBinding>()
    private var adapter by autoCleared<QuoteAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentQuoteBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuoteAdapter(object : QuoteActionListener {
            override fun onUrlOpen(source: SourceModel) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(source.url))
                startActivity(browserIntent)
            }

            override fun onQuoteAdd(quote: QuoteModel) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToQuoteEditor(quote.id)
                )
            }

            override fun onSourceAdd(source: SourceModel) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToSourceEditor(source.id)
                )
            }

            override fun onQuoteEdit(quote: QuoteModel) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToQuoteEditor(quote.id)
                )
            }

            override fun onSourceEdit(source: SourceModel) {
                findNavController().navigate(
                    ContentFragmentDirections.actionContentToSourceEditor(source.id)
                )
            }

            override fun onQuoteDelete(quote: QuoteModel) {

            }

            override fun onSourceDelete(source: SourceModel) {

            }
        })
        binding.recycler.adapter = adapter

        viewModel.content.observe(viewLifecycleOwner) { content ->
            when(content) {
                is ContentResModel.Success -> {
                    adapter.submitList(content.quotes)
                }
                is ContentResModel.Fail -> {}
            }
        }
    }

}*/

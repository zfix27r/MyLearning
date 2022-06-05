package ru.sergeyzabelin.mylearning

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import ru.sergeyzabelin.mylearning.adapters.DictionaryAdapter
import ru.sergeyzabelin.mylearning.databinding.FragmentDictionaryBinding
import ru.sergeyzabelin.mylearning.domain.MainViewModel

class DictionaryFragment : Fragment(), DictionaryAdapter.OnSelectedListener {
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var adapter: DictionaryAdapter

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentDictionaryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dictionaryFab.setOnClickListener {
            findNavController().navigate(R.id.action_navDictionaryFragment_to_navDictionaryAddFragment)
        }

        adapter = object : DictionaryAdapter(viewModel.getDictionary(), this@DictionaryFragment) {
            override fun onDataChanged() {

            }

            override fun onError(e: FirebaseFirestoreException) {
                Snackbar.make(binding.root, "error", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.dictionaryRecycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        menu.findItem(R.id.action_dictionary).isVisible = false
        menu.findItem(R.id.action_done).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSelected(document: DocumentSnapshot) {
        TODO("Not yet implemented")
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
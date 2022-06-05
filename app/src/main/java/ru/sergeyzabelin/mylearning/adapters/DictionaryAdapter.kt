package ru.sergeyzabelin.mylearning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import ru.sergeyzabelin.mylearning.data.entities.Dictionary
import ru.sergeyzabelin.mylearning.databinding.DictionaryRecyclerItemBinding

open class DictionaryAdapter(query: Query, private val listener: OnSelectedListener) :
    FirestoreAdapter<DictionaryAdapter.ViewHolder>(query) {

    interface OnSelectedListener {
        fun onSelected(document: DocumentSnapshot)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DictionaryRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(private val binding: DictionaryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnSelectedListener?
        ) {
            val dictionary = snapshot.toObject<Dictionary>() ?: return

            binding.dictionaryAddTitle.text = dictionary.title
            binding.dictionaryAddTitleOriginal.text = dictionary.titleOriginal
            binding.dictionaryAddDescription.text = dictionary.description

            binding.root.setOnClickListener {
                listener?.onSelected(snapshot)
            }
        }
    }
}
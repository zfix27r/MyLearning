package ru.sergeyzabelin.mylearning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import ru.sergeyzabelin.mylearning.data.entities.Pattern
import ru.sergeyzabelin.mylearning.databinding.RecyclerPatternItemBinding

open class CollectionAdapter(query: Query, private val listener: OnSelectedListener) :
    FirestoreAdapter<CollectionAdapter.ViewHolder>(query) {

    interface OnSelectedListener {
        fun onSelected(document: DocumentSnapshot)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            RecyclerPatternItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(private val binding: RecyclerPatternItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnSelectedListener?
        ) {
            if (snapshot.data.isNullOrEmpty()) {
                binding.patternTitle.text = snapshot.id

                binding.root.setOnClickListener {
                    listener?.onSelected(snapshot)
                }
            } else {
                val pattern = snapshot.toObject<Pattern>() ?: return
                binding.patternTitle.text = pattern.title
                binding.root.setOnClickListener {
                    listener?.onSelected(snapshot)
                }
            }
        }
    }
}
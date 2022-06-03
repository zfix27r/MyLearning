package ru.sergeyzabelin.mylearning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import ru.sergeyzabelin.mylearning.data.entities.LessonDetail
import ru.sergeyzabelin.mylearning.databinding.LessonDetailItemBinding

open class LessonDetailAdapter(query: Query, private val listener: OnDetailSelectedListener) :
    FirestoreAdapter<LessonDetailAdapter.ViewHolder>(query) {

    interface OnDetailSelectedListener {
        fun onDetailSelected(document: DocumentSnapshot)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LessonDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(val binding: LessonDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnDetailSelectedListener?
        ) {
            val detail = snapshot.toObject<LessonDetail>() ?: return

            binding.lessonDetailTitle.text = detail.title
            binding.lessonDetailDescription.text = detail.description
        }
    }

}
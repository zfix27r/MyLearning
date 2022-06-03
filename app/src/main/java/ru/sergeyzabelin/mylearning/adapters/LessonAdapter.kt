package ru.sergeyzabelin.mylearning.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import ru.sergeyzabelin.mylearning.data.entities.Lesson
import ru.sergeyzabelin.mylearning.databinding.LessonTopicItemBinding

open class LessonAdapter(query: Query, private val listener: OnLessonTopicSelectedListener) :
    FirestoreAdapter<LessonAdapter.ViewHolder>(query) {

    interface OnLessonTopicSelectedListener {
        fun onLessonTopicSelected(document: DocumentSnapshot)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LessonTopicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(private val binding: LessonTopicItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            snapshot: DocumentSnapshot,
            listener: OnLessonTopicSelectedListener?
        ) {
            val lessonTopic = snapshot.toObject<Lesson>() ?: return

            binding.lessonTopicMtvTitle.text = lessonTopic.title
            binding.lessonTopicMtvDescription.text = lessonTopic.description

            binding.root.setOnClickListener {
                listener?.onLessonTopicSelected(snapshot)
            }
        }
    }
}
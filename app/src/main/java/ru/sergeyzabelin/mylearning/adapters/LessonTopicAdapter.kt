package ru.sergeyzabelin.mylearning.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sergeyzabelin.mylearning.data.entities.LessonTopic
import ru.sergeyzabelin.mylearning.databinding.LessonTopicItemMainBinding


class LessonTopicAdapter(
    private val context: Context?
) :
    ListAdapter<LessonTopic, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder(
            LessonTopicItemMainBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]

        val mainViewHolder = holder as MainViewHolder
        mainViewHolder.bind(item)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class MainViewHolder(lessonTopicItemMainBinding: LessonTopicItemMainBinding) :
        RecyclerView.ViewHolder(lessonTopicItemMainBinding.root) {

        private val binding = lessonTopicItemMainBinding

        fun bind(lessonTopic: LessonTopic) {
            binding.lessonTopicMtvTitle.text = lessonTopic.title
            binding.lessonTopicMtvDescription.text = lessonTopic.description

/*            if (lessonTopic.image.isNotEmpty()) {
                context?.resources?.let {
                    binding.lessonTopicIvImage.setImageResource(
                        it.getIdentifier(
                            lessonTopic.image,
                            "drawable",
                            context.packageName
                        )
                    )
                }
            }*/
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<LessonTopic>() {
        override fun areItemsTheSame(oldItem: LessonTopic, newItem: LessonTopic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LessonTopic, newItem: LessonTopic): Boolean {
            return oldItem == newItem
        }
    }
}


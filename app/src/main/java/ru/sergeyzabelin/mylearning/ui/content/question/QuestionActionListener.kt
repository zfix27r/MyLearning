package ru.sergeyzabelin.mylearning.ui.content.question

import ru.zfix27r.data.model.db.Question

interface QuestionActionListener {

    fun onAdd(question: Question)

    fun onEdit(question: Question)

    fun onDelete(question: Question)
}
package ru.zfix27r.mylearning.ui.question

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

}
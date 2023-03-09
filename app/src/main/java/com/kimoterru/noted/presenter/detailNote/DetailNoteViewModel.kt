package com.kimoterru.noted.presenter.detailNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.usecase.detailnote.DeleteNoteFromLocalUseCase
import com.kimoterru.noted.domain.usecase.detailnote.GetNoteByIdFromLocalUseCase
import com.kimoterru.noted.domain.usecase.detailnote.UpdateNoteInLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailNoteViewModel(
    private val deleteNoteFromLocalUseCase: DeleteNoteFromLocalUseCase,
    private val getNoteByIdFromLocalUseCase: GetNoteByIdFromLocalUseCase,
    private val updateNoteInLocalUseCase: UpdateNoteInLocalUseCase
): ViewModel() {

    val note = MutableStateFlow(String())

    fun deleteNote(noteItem: NoteItem) = viewModelScope.launch {
        deleteNoteFromLocalUseCase.invoke(noteItem = noteItem)
    }

    fun updateNote(noteItem: NoteItem) = viewModelScope.launch {
        updateNoteInLocalUseCase.invoke(noteItem = noteItem)
    }

    fun getNoteFromLocal(id: Int) = viewModelScope.launch(Dispatchers.Unconfined) {
        note.emit(getNoteByIdFromLocalUseCase.invoke(id).content)
    }

}
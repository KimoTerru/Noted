package com.kimoterru.noted.presenter.newNote

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimoterru.noted.R
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.usecase.newnote.SaveNoteInLocalUseCase
import kotlinx.coroutines.launch

class NewNoteViewModel(
    private val saveNoteInLocalUseCase: SaveNoteInLocalUseCase
) : ViewModel() {

    fun addNote(noteItem: NoteItem, context: Context) = viewModelScope.launch {
        if (noteItem.content.isNotEmpty()) {
            saveNoteInLocalUseCase.invoke(noteItem)
        } else {
            Toast.makeText(context, "Empty!", Toast.LENGTH_SHORT).show()
        }
    }

}
package com.kimoterru.noted.domain.usecase.newnote

import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.repository.NoteRepository

class SaveNoteInLocalUseCase(
    private val repository: NoteRepository
) {

    suspend fun invoke(noteItem: NoteItem): Any {
        return repository.saveNoteInLocal(noteItem)
    }
}
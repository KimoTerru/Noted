package com.kimoterru.noted.domain.usecase.detailnote

import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.repository.NoteRepository

class UpdateNoteInLocalUseCase(
    private val repository: NoteRepository
) {

    suspend fun invoke(noteItem: NoteItem) {
        return repository.updateNoteInLocal(noteItem)
    }
}
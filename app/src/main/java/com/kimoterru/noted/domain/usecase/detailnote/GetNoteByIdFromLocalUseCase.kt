package com.kimoterru.noted.domain.usecase.detailnote

import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.repository.NoteRepository

class GetNoteByIdFromLocalUseCase(
    private val repository: NoteRepository
) {

    suspend fun invoke(id: Int): NoteItem {
        return repository.getNoteById(id = id)
    }
}
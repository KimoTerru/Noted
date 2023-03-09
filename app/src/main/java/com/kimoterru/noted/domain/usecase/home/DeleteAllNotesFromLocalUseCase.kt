package com.kimoterru.noted.domain.usecase.home

import com.kimoterru.noted.domain.repository.NoteRepository

class DeleteAllNotesFromLocalUseCase(
    private val repository: NoteRepository
) {
    suspend fun invoke() {
        return repository.deleteAllNotes()
    }
}
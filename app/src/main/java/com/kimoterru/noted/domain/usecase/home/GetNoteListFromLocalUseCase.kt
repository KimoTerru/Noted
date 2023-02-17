package com.kimoterru.noted.domain.usecase.home

import androidx.paging.PagingData
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteListFromLocalUseCase(
    private val repository: NoteRepository
) {

    suspend fun invoke(): Flow<PagingData<NoteItem>> {
        return repository.getNoteListFromLocal()
    }
}
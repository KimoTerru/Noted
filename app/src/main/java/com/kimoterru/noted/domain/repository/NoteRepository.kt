package com.kimoterru.noted.domain.repository

import androidx.paging.PagingData
import com.kimoterru.noted.domain.model.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNoteListFromLocal(): Flow<PagingData<NoteItem>>

    suspend fun saveNoteInLocal(noteItem: NoteItem)

    suspend fun deleteNoteFromLocal(noteItem: NoteItem)

    suspend fun updateNoteInLocal(noteItem: NoteItem)

    suspend fun setSearchBy(word: String)

    suspend fun setSortBy(sortBy: Int)

    suspend fun getNoteById(id: Int): NoteItem

    suspend fun deleteAllNotes()

}
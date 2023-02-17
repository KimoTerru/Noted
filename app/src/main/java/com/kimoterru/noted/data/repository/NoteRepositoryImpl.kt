package com.kimoterru.noted.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kimoterru.noted.data.local.NoteDao
import com.kimoterru.noted.data.mapper.toNoteEntity
import com.kimoterru.noted.data.mapper.toNoteItem
import com.kimoterru.noted.data.pagingsource.NotePagingSource
import com.kimoterru.noted.domain.model.NoteItem
import com.kimoterru.noted.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {

    override fun getNoteListFromLocal(): Flow<PagingData<NoteItem>> {
        return Pager(config = PagingConfig(
            pageSize = 50,
            enablePlaceholders = false,
            initialLoadSize = 50
        )) {
            NotePagingSource(noteDao)
        }.flow
    }

    override suspend fun saveNoteInLocal(noteItem: NoteItem) {
        return noteDao.saveNoteInLocal(noteItem.toNoteEntity())
    }

    override suspend fun deleteNoteFromLocal(noteItem: NoteItem) {
        return noteDao.deleteNoteFromLocal(noteItem.toNoteEntity())
    }

    override suspend fun updateNoteInLocal(noteItem: NoteItem) {
        return noteDao.updateNoteInLocal(noteItem.toNoteEntity())
    }

    override suspend fun getNoteById(id: Int): NoteItem {
        return noteDao.getNoteById(id).toNoteItem()
    }

}
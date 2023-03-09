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
import kotlinx.coroutines.flow.MutableStateFlow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {

    private val mutableSearchBy = MutableStateFlow(String())
    private val mutableSortBy = MutableStateFlow(1)

    private val pageSize = 50
    private val initialLoadSize = 50

    override fun getNoteListFromLocal(): Flow<PagingData<NoteItem>> {
        return Pager(config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false,
            initialLoadSize = initialLoadSize
        )) {
            NotePagingSource(
                noteDao,
                mutableSearchBy.value,
                mutableSortBy.value
            )
        }.flow
    }

    override suspend fun saveNoteInLocal(noteItem: NoteItem) {
        return noteDao.saveNote(noteItem.toNoteEntity())
    }

    override suspend fun deleteNoteFromLocal(noteItem: NoteItem) {
        return noteDao.deleteNote(noteItem.toNoteEntity())
    }

    override suspend fun updateNoteInLocal(noteItem: NoteItem) {
        return noteDao.updateNote(noteItem.toNoteEntity())
    }

    override suspend fun setSearchBy(word: String) {
        mutableSearchBy.emit(value = word)
    }

    override suspend fun setSortBy(sortBy: Int) {
        mutableSortBy.emit(value = sortBy)
    }

    override suspend fun getNoteById(id: Int): NoteItem {
        return noteDao.getNoteById(id).toNoteItem()
    }

    override suspend fun deleteAllNotes() {
        return noteDao.deleteAll()
    }

}
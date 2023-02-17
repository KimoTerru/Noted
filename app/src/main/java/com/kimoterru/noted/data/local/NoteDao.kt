package com.kimoterru.noted.data.local

import androidx.room.*
import com.kimoterru.noted.data.local.model.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllNoteFromLocal(limit: Int, offset: Int): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNoteInLocal(vararg noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNoteFromLocal(noteEntity: NoteEntity)

    @Update
    suspend fun updateNoteInLocal(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table WHERE id IN (:id)")
    suspend fun getNoteById(id: Int): NoteEntity

}
package com.kimoterru.noted.data.local

import androidx.room.*
import com.kimoterru.noted.data.local.model.NoteEntity

@Dao
interface NoteDao {

    @Query(
        "SELECT * FROM note_table " +
                "WHERE :searchBy = '' OR content LIKE '%' || :searchBy || '%'" +
                "ORDER BY " +
                "CASE WHEN :sortBy = 1 THEN id END ASC, " +
                "CASE WHEN :sortBy = 2 THEN id END DESC " + "LIMIT :limit OFFSET :offset"
    )
    suspend fun getAllNotes(searchBy: String, sortBy: Int, limit: Int, offset: Int): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(vararg noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table WHERE id IN (:id)")
    suspend fun getNoteById(id: Int): NoteEntity

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

}
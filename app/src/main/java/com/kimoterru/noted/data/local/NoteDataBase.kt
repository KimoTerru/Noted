package com.kimoterru.noted.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimoterru.noted.data.local.model.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class NoteDataBase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}
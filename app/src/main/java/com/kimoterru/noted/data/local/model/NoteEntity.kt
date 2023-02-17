package com.kimoterru.noted.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "note_table", indices = [Index(value = ["content"], unique = true)])
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String
)
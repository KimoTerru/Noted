package com.kimoterru.noted.data.mapper

import com.kimoterru.noted.data.local.model.NoteEntity
import com.kimoterru.noted.domain.model.NoteItem

fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        id = id,
        content = content
    )
}

fun NoteItem.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        content = content
    )
}
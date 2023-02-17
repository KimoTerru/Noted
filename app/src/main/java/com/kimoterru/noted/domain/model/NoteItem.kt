package com.kimoterru.noted.domain.model

data class NoteItem(
    val id: Int,
    val content: String
) {
    companion object {
        const val DEFAULT_ID_NOTE = 0
    }
}
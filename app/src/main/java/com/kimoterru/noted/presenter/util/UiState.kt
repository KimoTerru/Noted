package com.kimoterru.noted.presenter.util

import com.kimoterru.noted.domain.model.NoteItem

data class UiState<T>(
    val data: List<Any> = emptyList(),
    val isLoading: Boolean = false
) {
    val isEmptyData get() = !isLoading && data.isEmpty()
    val isNotEmptyData get() = !isLoading && data.isNotEmpty()
}
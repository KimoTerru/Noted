package com.kimoterru.noted.domain.usecase.home

import com.kimoterru.noted.domain.repository.NoteRepository

class SetSortByUseCase(
    private val repository: NoteRepository
) {
    suspend fun invoke(sortBy: Int) {
        return repository.setSortBy(sortBy = sortBy)
    }
}
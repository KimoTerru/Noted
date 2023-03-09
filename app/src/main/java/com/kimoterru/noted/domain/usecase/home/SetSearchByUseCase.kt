package com.kimoterru.noted.domain.usecase.home

import com.kimoterru.noted.domain.repository.NoteRepository

class SetSearchByUseCase (
    private val repository: NoteRepository
) {
    suspend fun invoke(word:String) {
        return repository.setSearchBy(word)
    }
}
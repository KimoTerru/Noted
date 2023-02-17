package com.kimoterru.noted.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimoterru.noted.domain.usecase.home.GetNoteListFromLocalUseCase

class HomeViewModel(
    private val getNoteListFromLocalUseCase: GetNoteListFromLocalUseCase
): ViewModel() {

    suspend fun getListNotes() =  getNoteListFromLocalUseCase.invoke().cachedIn(viewModelScope)
}
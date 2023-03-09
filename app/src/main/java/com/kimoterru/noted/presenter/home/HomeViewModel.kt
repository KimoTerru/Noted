package com.kimoterru.noted.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kimoterru.noted.domain.usecase.home.GetNoteListFromLocalUseCase
import com.kimoterru.noted.domain.usecase.home.SetSearchByUseCase
import com.kimoterru.noted.domain.usecase.home.SetSortByUseCase
import com.kimoterru.noted.domain.usecase.home.DeleteAllNotesFromLocalUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNoteListFromLocalUseCase: GetNoteListFromLocalUseCase,
    private val setSearchByUseCase: SetSearchByUseCase,
    private val setSortByUseCase: SetSortByUseCase,
    private val deleteAllNotesFromLocalUseCase: DeleteAllNotesFromLocalUseCase
): ViewModel() {

    fun getListNotes() =  getNoteListFromLocalUseCase.invoke().cachedIn(viewModelScope)

    fun searchWordInNote(word: String) = viewModelScope.launch {
        setSearchByUseCase.invoke(word = word)
    }

    fun sortByNotes(sortBy: Int) = viewModelScope.launch {
        setSortByUseCase.invoke(sortBy = sortBy)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        deleteAllNotesFromLocalUseCase.invoke()
    }
}
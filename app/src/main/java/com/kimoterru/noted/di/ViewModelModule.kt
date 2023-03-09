package com.kimoterru.noted.di

import com.kimoterru.noted.presenter.detailNote.DetailNoteViewModel
import com.kimoterru.noted.presenter.home.HomeViewModel
import com.kimoterru.noted.presenter.newNote.NewNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NewNoteViewModel(
            saveNoteInLocalUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            getNoteListFromLocalUseCase = get(),
            setSearchByUseCase = get(),
            setSortByUseCase = get(),
            deleteAllNotesFromLocalUseCase = get()
        )
    }

    viewModel {
        DetailNoteViewModel(
            deleteNoteFromLocalUseCase = get(),
            getNoteByIdFromLocalUseCase = get(),
            updateNoteInLocalUseCase = get()
        )
    }

}
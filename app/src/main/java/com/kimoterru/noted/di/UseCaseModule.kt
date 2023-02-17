package com.kimoterru.noted.di

import com.kimoterru.noted.domain.usecase.detailnote.DeleteNoteFromLocalUseCase
import com.kimoterru.noted.domain.usecase.detailnote.GetNoteByIdFromLocalUseCase
import com.kimoterru.noted.domain.usecase.detailnote.UpdateNoteInLocalUseCase
import com.kimoterru.noted.domain.usecase.newnote.SaveNoteInLocalUseCase
import com.kimoterru.noted.domain.usecase.home.GetNoteListFromLocalUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNoteListFromLocalUseCase(repository = get()) }
    factory { DeleteNoteFromLocalUseCase(repository = get()) }
    factory { SaveNoteInLocalUseCase(repository = get()) }
    factory { UpdateNoteInLocalUseCase(repository = get()) }
    factory { GetNoteByIdFromLocalUseCase(repository = get()) }
}
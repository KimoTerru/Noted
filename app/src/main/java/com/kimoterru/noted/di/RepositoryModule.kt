package com.kimoterru.noted.di

import com.kimoterru.noted.data.repository.NoteRepositoryImpl
import com.kimoterru.noted.domain.repository.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NoteRepository> { NoteRepositoryImpl(noteDao = get()) }
}
package com.kimoterru.noted.di

import androidx.room.Room
import com.kimoterru.noted.data.local.NoteDataBase
import com.kimoterru.noted.presenter.util.DATABASE_NAME
import org.koin.dsl.module

val localModule = module {
    single (createdAtStart = true) {
        Room.databaseBuilder(
            get(), NoteDataBase::class.java, DATABASE_NAME
        ).build()
    }
    single (createdAtStart = true) { get<NoteDataBase>().getNoteDao() }
}
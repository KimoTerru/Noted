package com.kimoterru.noted

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.kimoterru.noted.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class NoteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NoteApplication)
            modules(appModule)
        }
    }

}
package com.kimoterru.noted

import android.app.Application
import com.google.android.material.color.DynamicColors

class NoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
package com.ddd.twinkle.safehero.app

import android.app.Application
import timber.log.Timber

class SafeHeroApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
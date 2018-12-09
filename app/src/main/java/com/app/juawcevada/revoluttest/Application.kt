package com.app.juawcevada.revoluttest

import com.app.juawcevada.revoluttest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree


class Application : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
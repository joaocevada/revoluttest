package com.app.juawcevada.revoluttest.di

import android.content.Context
import com.app.juawcevada.revoluttest.Application
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context
}
package com.app.juawcevada.revoluttest.di

import com.app.juawcevada.revoluttest.ui.MainActivity
import com.app.juawcevada.revoluttest.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}
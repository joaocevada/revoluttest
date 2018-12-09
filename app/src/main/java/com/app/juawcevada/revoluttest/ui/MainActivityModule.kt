package com.app.juawcevada.revoluttest.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [CurrencyExchangeFragmentModule::class])
    internal abstract fun contributeCharacterDetailFragment(): CurrencyExchangeFragment
}
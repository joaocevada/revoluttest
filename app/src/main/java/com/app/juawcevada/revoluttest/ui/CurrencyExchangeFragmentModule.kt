package com.app.juawcevada.revoluttest.ui

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class CurrencyExchangeFragmentModule {

    @Binds
    internal abstract fun bindFragment(fragment: CurrencyExchangeFragment): Fragment
}
package com.app.juawcevada.revoluttest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.juawcevada.revoluttest.ui.CurrencyExchangeViewModel
import com.app.juawcevada.revoluttest.ui.shared.ViewModelFactory
import com.app.juawcevada.rickspace.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides the view model factory with view models maps and binds the ViewModelFactory type to
 * a ViewModelProvider.Factory type.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyExchangeViewModel::class)
    internal abstract fun bindCharacterDetailsViewModel(userViewModel: CurrencyExchangeViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
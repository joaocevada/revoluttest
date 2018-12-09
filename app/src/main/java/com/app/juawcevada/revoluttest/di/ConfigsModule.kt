package com.app.juawcevada.revoluttest.di

import com.app.juawcevada.rickspace.di.annotation.ApiConfig
import dagger.Module
import dagger.Provides


@Module
class ConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return "https://revolut.duckdns.org"
    }
}
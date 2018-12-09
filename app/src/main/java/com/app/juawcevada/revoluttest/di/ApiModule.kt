package com.app.juawcevada.revoluttest.di

import com.app.juawcevada.revoluttest.data.ExchangeService
import com.app.juawcevada.rickspace.di.annotation.ApiConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


/**
 * App module
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideApi(
        @ApiConfig url: String,
        okHttpClient: OkHttpClient): ExchangeService {

        val retrofit = Retrofit.Builder()
                .callFactory(okHttpClient)
                .baseUrl(url)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        return retrofit.create(ExchangeService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
}
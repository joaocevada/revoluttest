package com.app.juawcevada.revoluttest.di

import com.app.juawcevada.revoluttest.Application
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ApiModule::class,
    ConfigsModule::class,
    ViewModelModule::class,
    ActivityBuildersModule::class
])
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}
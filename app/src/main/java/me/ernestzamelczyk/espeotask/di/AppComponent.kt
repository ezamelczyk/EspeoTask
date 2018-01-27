package me.ernestzamelczyk.espeotask.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.ernestzamelczyk.espeotask.App
import me.ernestzamelczyk.espeotask.AppModule
import me.ernestzamelczyk.espeotask.data.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class,
    DataModule::class
])
interface AppComponent: AndroidInjector<App>
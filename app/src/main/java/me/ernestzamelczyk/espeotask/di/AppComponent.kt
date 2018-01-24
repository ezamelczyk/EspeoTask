package me.ernestzamelczyk.espeotask.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.ernestzamelczyk.espeotask.App
import me.ernestzamelczyk.espeotask.AppModule
import me.ernestzamelczyk.espeotask.data.DataModule
import me.ernestzamelczyk.espeotask.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class,
    DataModule::class,
    ViewModelModule::class
])
interface AppComponent: AndroidInjector<App>
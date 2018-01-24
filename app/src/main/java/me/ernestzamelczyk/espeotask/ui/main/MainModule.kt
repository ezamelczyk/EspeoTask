package me.ernestzamelczyk.espeotask.ui.main

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.ernestzamelczyk.espeotask.di.viewmodel.ViewModelFactory

@Module
abstract class MainModule {

    @Module
    companion object {
        @Provides
        fun mainViewModel(mainActivity: MainActivity, viewModelFactory: ViewModelFactory): MainViewModel {
            return ViewModelProviders.of(mainActivity, viewModelFactory).get(MainViewModel::class.java)
        }
    }

}
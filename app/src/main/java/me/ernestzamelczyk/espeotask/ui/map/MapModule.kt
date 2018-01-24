package me.ernestzamelczyk.espeotask.ui.map

import android.arch.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.ernestzamelczyk.espeotask.di.viewmodel.ViewModelFactory

@Module
abstract class MapModule {

    @Module
    companion object {
        @Provides
        fun mapViewModel(mapActivity: MapActivity, viewModelFactory: ViewModelFactory): MapViewModel {
            return ViewModelProviders.of(mapActivity, viewModelFactory).get(MapViewModel::class.java)
        }
    }

}
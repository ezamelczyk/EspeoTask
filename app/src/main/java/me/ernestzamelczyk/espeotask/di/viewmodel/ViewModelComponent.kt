package me.ernestzamelczyk.espeotask.di.viewmodel

import android.support.v4.app.FragmentActivity
import dagger.BindsInstance
import dagger.Component
import me.ernestzamelczyk.espeotask.ui.main.MainViewModel

@Component(modules = [
    ViewModelModule::class
])
interface ViewModelComponent {
    fun inject(viewModel: MainViewModel)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragmentActivity(fragmentActivity: FragmentActivity): Builder

        fun build(): ViewModelComponent
    }
}
package me.ernestzamelczyk.espeotask.ui.main.apps

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [AppsSubcomponent::class])
abstract class AppsModule {
    @Binds
    @IntoMap
    @FragmentKey(AppsFragment::class)
    abstract fun bindExperienceFragmentInjectorFactory(builder: AppsSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}
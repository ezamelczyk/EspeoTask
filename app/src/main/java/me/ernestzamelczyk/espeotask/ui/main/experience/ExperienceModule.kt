package me.ernestzamelczyk.espeotask.ui.main.experience

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [ ExperienceSubcomponent::class ])
abstract class ExperienceModule {
    @Binds
    @IntoMap
    @FragmentKey(ExperienceFragment::class)
    abstract fun bindExperienceFragmentInjectorFactory(builder: ExperienceSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}
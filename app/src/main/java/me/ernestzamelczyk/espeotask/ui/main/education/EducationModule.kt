package me.ernestzamelczyk.espeotask.ui.main.education

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [ EducationSubcomponent::class ])
abstract class EducationModule {
    @Binds
    @IntoMap
    @FragmentKey(EducationFragment::class)
    abstract fun bindExperienceFragmentInjectorFactory(builder: EducationSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}
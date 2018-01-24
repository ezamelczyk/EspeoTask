package me.ernestzamelczyk.espeotask.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.ernestzamelczyk.espeotask.ui.main.MainActivity
import me.ernestzamelczyk.espeotask.ui.main.MainModule
import me.ernestzamelczyk.espeotask.ui.main.apps.AppsModule
import me.ernestzamelczyk.espeotask.ui.main.education.EducationModule
import me.ernestzamelczyk.espeotask.ui.main.experience.ExperienceModule
import me.ernestzamelczyk.espeotask.ui.map.MapActivity
import me.ernestzamelczyk.espeotask.ui.map.MapModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [
        MainModule::class,
        ExperienceModule::class,
        EducationModule::class,
        AppsModule::class
    ])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [
        MapModule::class
    ])
    abstract fun mapActivity(): MapActivity

}

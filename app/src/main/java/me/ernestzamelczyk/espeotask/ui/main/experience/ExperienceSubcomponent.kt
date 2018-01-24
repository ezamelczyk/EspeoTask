package me.ernestzamelczyk.espeotask.ui.main.experience

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ExperienceSubcomponent: AndroidInjector<ExperienceFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<ExperienceFragment>()
}
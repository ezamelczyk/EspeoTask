package me.ernestzamelczyk.espeotask.ui.main.education

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent()
interface EducationSubcomponent: AndroidInjector<EducationFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<EducationFragment>()
}

package me.ernestzamelczyk.espeotask.ui.main.apps

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface AppsSubcomponent: AndroidInjector<AppsFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<AppsFragment>()
}
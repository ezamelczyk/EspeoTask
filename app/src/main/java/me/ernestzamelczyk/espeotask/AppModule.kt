package me.ernestzamelczyk.espeotask

import android.content.Context
import dagger.Module
import dagger.Provides
import me.ernestzamelczyk.espeotask.di.AppContext
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides @Singleton @AppContext
    fun applicationContext(): Context = app.applicationContext

}
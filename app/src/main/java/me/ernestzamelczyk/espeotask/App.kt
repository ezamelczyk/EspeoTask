package me.ernestzamelczyk.espeotask

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.ernestzamelczyk.espeotask.di.DaggerAppComponent
import timber.log.Timber

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
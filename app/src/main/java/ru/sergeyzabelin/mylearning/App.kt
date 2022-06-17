package ru.sergeyzabelin.mylearning

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.sergeyzabelin.mylearning.di.AppInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }
}
package com.andreamw96.moviecatalogue.di

import android.app.Application
import com.andreamw96.moviecatalogue.BaseApplication
import com.andreamw96.moviecatalogue.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
                AndroidSupportInjectionModule::class,
                ActivityBuildersModule::class,
                AppModule::class,
                DataModule::class,
                ViewModelFactoryModule::class
        ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

}
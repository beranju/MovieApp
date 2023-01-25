package com.nextgen.movieapp

import android.app.Application
import com.nextgen.movieapp.di.appModule
import com.nextgen.movieapp.di.networkModule
import com.nextgen.movieapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApp: Application() {
    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@BaseApp)
            modules(listOf(
                networkModule,
                repositoryModule,
                appModule,
            ))
        }
        super.onCreate()
    }
}
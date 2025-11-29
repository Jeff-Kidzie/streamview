package com.kidzie.streamview

import android.app.Application
import com.kidzie.streamview.di.dataSourceModule
import com.kidzie.streamview.di.networkModule
import com.kidzie.streamview.di.repositoryModule
import com.kidzie.streamview.di.useCaseModule
import com.kidzie.streamview.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StreamApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@StreamApplication)
            modules(
                networkModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}
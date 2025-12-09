package com.aiden.qrcraft

import android.app.Application
import com.aiden.qrcraft.di.appModule
import com.aiden.qrcraft.presentation.presentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by AidenChang on 2025/12/6
 */
class MainApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default) // 適合 CPU 密集型工作

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                appModule,
                presentationModule
            )
        }
    }
}
package com.aiden.qrcraft.di

import com.aiden.qrcraft.MainApplication
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by AidenChang on 2025/12/6
 */
val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as MainApplication).applicationScope
    }
}
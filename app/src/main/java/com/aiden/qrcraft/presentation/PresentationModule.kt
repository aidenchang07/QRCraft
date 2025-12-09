package com.aiden.qrcraft.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by AidenChang on 2025/12/6
 */
val presentationModule = module {
    viewModelOf(::ScanViewModel)
}
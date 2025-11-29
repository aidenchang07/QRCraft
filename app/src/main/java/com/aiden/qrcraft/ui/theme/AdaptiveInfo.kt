package com.aiden.qrcraft.ui.theme

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

/**
 * Created by AidenChang on 2025/11/29
 */
@Composable
fun currentScreenType(windowSizeClass: WindowSizeClass): ScreenType {
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> ScreenType.Compact
        WindowWidthSizeClass.Medium -> ScreenType.Medium
        else -> ScreenType.Compact
    }
}

sealed class ScreenType {
    object Compact : ScreenType()
    object Medium : ScreenType()
}
package com.aiden.qrcraft.ui.theme

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by AidenChang on 2025/11/30
 */
fun WindowSizeClass.provideDimension(): AppDimensions {
    return when (this.widthSizeClass) { // 示範共用這麼寫，看後面需不需要用
        WindowWidthSizeClass.Compact -> AppDimensions(
            cardWidth = 300.dp
        )
        WindowWidthSizeClass.Medium -> AppDimensions(
            cardWidth = 480.dp
        )
        else -> AppDimensions(
            cardWidth = 300.dp
        )
    }
}

data class AppDimensions(
    val cardWidth: Dp
)
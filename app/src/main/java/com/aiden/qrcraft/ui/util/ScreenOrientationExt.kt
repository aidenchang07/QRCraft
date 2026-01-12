package com.aiden.qrcraft.ui.util

import android.view.OrientationEventListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * Created by AidenChang on 2026/1/8
 */
@Composable
fun rememberScreenOrientation(): State<DeviceOrientation> {
    val context = LocalContext.current
    val orientationState = remember {
        mutableStateOf<DeviceOrientation>(DeviceOrientation.Portrait)
    }

    DisposableEffect(context) {
        val orientationEventListener = object : OrientationEventListener(context) {
            override fun onOrientationChanged(orientation: Int) {
                // 忽略無效的角度
                if (orientation == ORIENTATION_UNKNOWN) return

                // 值不一樣更新狀態
                val newOrientation = DeviceOrientation.fromAngle(orientation)
                if (newOrientation != orientationState.value) {
                    orientationState.value = newOrientation
                }
            }
        }

        orientationEventListener.enable()

        onDispose {
            orientationEventListener.disable()
        }
    }
    return orientationState
}

/**
 * 用於表示設備的四個主要物理方向。
 *
 * @property angle 該方向對應的 UI 旋轉角度 (0f, 90f, 180f, 270f)。
 */
sealed class DeviceOrientation(val angle: Float) {
    /** 直立 */
    data object Portrait : DeviceOrientation(0f)

    /** 向右橫放 (順時針90度) */
    data object Landscape : DeviceOrientation(90f)

    /** 倒置 */
    data object InvertedPortrait : DeviceOrientation(180f)

    /** 向左橫放 (逆時針90度) */
    data object InvertedLandscape : DeviceOrientation(270f)

    companion object {
        /**
         * 根據感應器回傳的原始角度 (0-359)，
         * 將其轉換為我們定義的 DeviceOrientation 狀態。
         * @param orientation 來自 OrientationEventListener 的原始角度。
         */
        fun fromAngle(orientation: Int): DeviceOrientation {
            return when(orientation) {
                // 範圍: 45° ~ 134°。物理上向右橫放。
                in 45..134 -> Landscape
                // 範圍: 135° ~ 224°。物理上上下顛倒。
                in 135..224 -> InvertedPortrait
                // 範圍: 225° ~ 314°。物理上向左橫放。
                in 225..314 -> InvertedLandscape
                // 範圍: 315° ~ 44°。物理上正常直立。
                else -> Portrait
            }
        }
    }
}
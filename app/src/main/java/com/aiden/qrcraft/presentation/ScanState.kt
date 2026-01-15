package com.aiden.qrcraft.presentation

/**
 * Created by AidenChang on 2025/12/5
 */
sealed class ScanState {
    data object Loading : ScanState()
}
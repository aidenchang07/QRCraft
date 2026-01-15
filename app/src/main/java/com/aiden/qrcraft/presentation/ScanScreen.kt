package com.aiden.qrcraft.presentation

import android.content.pm.ActivityInfo
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

/**
 * Created by AidenChang on 2025/12/5
 */
@Composable
fun ScanRoot(
    viewModel: ScanViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val activity = LocalActivity.current

    DisposableEffect(Unit) {
        activity?.let { activity ->
            // 保持畫面直立
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            onDispose {
                // 取消畫面直立（可旋轉）
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        } ?: onDispose { }
    }

    ScanScreen(uiState)
}

@Composable
fun ScanScreen(
    uiState: ScanState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        ScanState.Loading -> {
            // TODO: 待加入功能
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanScreenPreview() {
    ScanScreen(
        uiState = ScanState.Loading
    )
}
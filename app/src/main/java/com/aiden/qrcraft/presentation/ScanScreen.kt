package com.aiden.qrcraft.presentation

import androidx.compose.runtime.Composable
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
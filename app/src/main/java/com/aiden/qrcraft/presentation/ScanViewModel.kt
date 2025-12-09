package com.aiden.qrcraft.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by AidenChang on 2025/12/5
 */
class ScanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ScanState>(ScanState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _intent = Channel<ScanIntent>()
    val intent = _intent.receiveAsFlow()

    private val _effect = Channel<ScanEffect>()
    val effect = _effect.receiveAsFlow()

    fun sendIntent(intent: ScanIntent) {
        viewModelScope.launch {
            _intent.send(intent)
        }
    }
}
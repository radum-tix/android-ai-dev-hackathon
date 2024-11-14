package com.android.ai.dev.aichat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed interface Message {
    data class TextFromYou(val content: String) : Message
    data class ImageFromYou(val content: Bitmap) : Message
    data class TextFromGemini(val content: String) : Message
    data object GeminiIsThinking : Message
}

data class UiState(
    val messages: List<Message> = listOf()
)

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState>
        get() = _uiState

    fun onNewMessageFromUser(message: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            messages = currentState.messages + Message.TextFromYou(message) + Message.GeminiIsThinking
        )
    }

    fun onNewImageFromUser(image: Bitmap) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            messages = currentState.messages + Message.ImageFromYou(image) + Message.GeminiIsThinking
        )
    }
}
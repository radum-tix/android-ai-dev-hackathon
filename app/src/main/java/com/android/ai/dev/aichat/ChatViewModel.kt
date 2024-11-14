package com.android.ai.dev.aichat

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.API_KEY
    )
    private val chat = generativeModel.startChat()

    fun onNewMessageFromUser(message: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            messages = currentState.messages + Message.TextFromYou(message) + Message.GeminiIsThinking
        )
        viewModelScope.launch {
            val response = chat.sendMessage(message).text
            if (!response.isNullOrEmpty()) {
                val updatedState = _uiState.value
                _uiState.value = updatedState.copy(
                    messages = updatedState.messages - Message.GeminiIsThinking + Message.TextFromGemini(response)
                )
            }
        }
    }

    fun onNewImageFromUser(image: Bitmap) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            messages = currentState.messages + Message.ImageFromYou(image) + Message.GeminiIsThinking
        )
        viewModelScope.launch {
            val response = chat.sendMessage(image).text
            if (!response.isNullOrEmpty()) {
                val updatedState = _uiState.value
                _uiState.value = updatedState.copy(
                    messages = updatedState.messages - Message.GeminiIsThinking + Message.TextFromGemini(response)
                )
            }
        }
    }
}
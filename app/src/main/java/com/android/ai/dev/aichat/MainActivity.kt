package com.android.ai.dev.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.ai.dev.aichat.ui.theme.AIChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Step 1: Intro in Compose: create speech bubbles for chat
        // Step 2: Create a material theme: https://material-foundation.github.io/material-theme-builder/
        // Step 3: Intro into State and Recomposition: finalize the elements for the chat: Text Input, Take Image
        // Step 4: Intro in dynamic lists
        // Step 5: Intro in view models
        // Step 6: Intro in coroutines
        // Step 7: Intro in AI Studio
        // Step 8: Finish up chatbot with generative AI model
        // Optionally, if time: include content generation
        setContent {
            AIChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AIChatTheme {
        Greeting("Android")
    }
}
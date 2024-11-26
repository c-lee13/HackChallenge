package com.example.hackchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hackchallenge.ui.theme.HackChallengeTheme
import com.example.hackchallenge.ui.theme.screens.createScreenLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HackChallengeTheme {
                createScreenLayout()
            }
        }
    }
}

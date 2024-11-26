package com.example.hackchallenge.data

import androidx.compose.ui.graphics.painter.Painter

data class StudySpace(
    val name: String,
    val noiseLevels: String, //change to noise levels later
    val photo: Painter?,
    val mapsAttributes: MapsAttributes?
)

data class MapsAttributes(
    val occupancyLimit: Int,
    val openHours: String,
)

enum class NoiseLevels(val description: String) {
    QUIET("Quiet"),
    MODERATE("Moderate"),
    NOISY("Noisy"),
    VERY_NOISY("Very Noisy")
}

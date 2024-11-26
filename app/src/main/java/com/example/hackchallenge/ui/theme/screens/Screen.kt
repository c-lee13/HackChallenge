package com.example.hackchallenge.ui.theme.screens

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    data object StudySpaceScreen : Screen("StudySpaceScreen")

    @Serializable
    data object ProfileScreen : Screen("ProfileScreen")
}

//defining bottom tab
data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)
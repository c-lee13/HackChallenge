package com.example.hackchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hackchallenge.ui.theme.HackChallengeTheme
import com.example.hackchallenge.ui.theme.screens.NavItem
import com.example.hackchallenge.ui.theme.screens.ProfileScreen
import com.example.hackchallenge.ui.theme.screens.ReservationScreen
import com.example.hackchallenge.ui.theme.screens.Screen
import com.example.hackchallenge.ui.theme.screens.StudySpaceScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //navigation setup
            val navController = rememberNavController()
            val tabs = listOf(
                NavItem(Screen.ReservationScreen, "Reservations", Icons.Filled.DateRange),
                NavItem(Screen.StudySpaceScreen, "Study Spaces", Icons.Filled.Home),
                NavItem(Screen.ProfileScreen, "Profile", Icons.Filled.Person)
            )
            val navBackStackEntry = navController.currentBackStackEntryAsState().value

            HackChallengeTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            tabs.map { item ->
                                NavigationBarItem(
                                    selected = item.screen == navBackStackEntry?.toScreen(),
                                    onClick = { navController.navigate(item.screen.route) },
                                    label = { Text(text = item.label) },
                                    icon = { Icon(item.icon, contentDescription = null) }
                                )
                            }
                        }
                    }
                ) { innerPadding -> //ensure box doesnt overlap with bottom bar
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.StudySpaceScreen.route
                        ) {

                            composable(Screen.ReservationScreen.route) {
                                ReservationScreen()
                            }

                            composable(Screen.StudySpaceScreen.route) {
                                //define content of screen
                                StudySpaceScreen()
                            }

                            composable(Screen.ProfileScreen.route) {
                                ProfileScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "StudySpaceScreen" -> Screen.StudySpaceScreen
            "ProfileScreen" -> Screen.ProfileScreen
            "ReservationScreen" -> Screen.ReservationScreen
            else -> null
        }
}
package com.example.hackchallenge.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackchallenge.data.StudySpace

@Composable
fun ReservationScreen(viewModel: ReservationScreenViewModel = hiltViewModel()) {
    val viewState = viewModel.reservationScreenViewState.collectAsState().value

    val reservationList = viewState.reservationList

    CreateReservationScreen(reservationList)
}

@Composable
fun CreateReservationScreen(reservationList: List<StudySpace>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Your Reservations",
            fontSize = 30.sp
        )

        if (reservationList.isEmpty()) {
            Text(text = "No reservations yet!") // Fallback text
        } else {
            val lazyListState = rememberLazyListState()
            LazyColumn(state = lazyListState) {
                items(reservationList) { item ->
                    DisplayIndividualReservation(item)
                }
            }
        }
    }
}

@Composable
fun DisplayIndividualReservation(item: StudySpace) {
    if (item.photo != null) {
        Image(
            painter = item.photo,
            contentDescription = "Photo of reservation"
        )
    }
    Text(text = item.name)
}
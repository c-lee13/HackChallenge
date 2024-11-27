package com.example.hackchallenge.ui.theme.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackchallenge.data.StudySpace
import com.example.hackchallenge.data.StudySpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class ReservationScreenViewState(
    val reservationList: List<StudySpace>
)

@HiltViewModel
class ReservationScreenViewModel @Inject constructor(
    private val repository: StudySpaceRepository //TODO change
) : ViewModel() {

    private val reservationFlow = MutableStateFlow(emptyList<StudySpace>())

    val reservationScreenViewState: StateFlow<ReservationScreenViewState> =
        reservationFlow.map { reservations ->
            Log.d("REservation size: " + reservations.size, "regerge")
            ReservationScreenViewState(reservations)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ReservationScreenViewState(emptyList())
        )

    fun addReservation(reservation: StudySpace) {
        if (reservation !in reservationFlow.value) {
            Log.d("adding reservation", "egregrege")
            reservationFlow.value = reservationFlow.value + reservation
        }
    }

    fun removeReservation(reservation: StudySpace) {
        if (reservation in reservationFlow.value) {
            reservationFlow.value = reservationFlow.value - reservation
        }
    }
}
package com.example.hackchallenge.ui.theme.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackchallenge.data.StudySpace
import com.example.hackchallenge.data.StudySpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class StudySpaceScreenViewState(
    val studySpaces: List<StudySpace> = emptyList(),
    val preferences: List<String> = emptyList(),
    val allAttributes: List<String> = emptyList()
)

@HiltViewModel
class StudySpaceScreenViewModel @Inject constructor(
    private val repository: StudySpaceRepository
) : ViewModel() {

    private val preferencesFlow = MutableStateFlow(emptyList<String>())
    private val allAttributesFlow = MutableStateFlow(emptyList<String>())

    val studySpaceScreenViewState: StateFlow<StudySpaceScreenViewState> =
        combine(preferencesFlow, allAttributesFlow) { preferences, allAttributes ->
            StudySpaceScreenViewState(emptyList(), preferences, allAttributes)
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            StudySpaceScreenViewState(emptyList(), emptyList(), emptyList())
        )

    init {
        //TODO change once backend done
        allAttributesFlow.value =
            allAttributesFlow.value + "Quiet" + "Private Room" + "Open until Midnight" + "Food Nearby" + "Group Room" + "Reservable"
    }

    fun addPreference(preference: String) {
        if (preference !in preferencesFlow.value) {
            preferencesFlow.value = preferencesFlow.value + preference
        }
    }

    fun removePreference(preference: String) {
        if (preference in preferencesFlow.value) {
            preferencesFlow.value = preferencesFlow.value - preference
        }
    }


}
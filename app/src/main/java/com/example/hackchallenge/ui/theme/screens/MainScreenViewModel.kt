package com.example.hackchallenge.ui.theme.screens

import androidx.lifecycle.ViewModel
import com.example.hackchallenge.data.StudySpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class MainScreenViewState(
    val studySpaces: List<String> = emptyList()
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: StudySpaceRepository
) : ViewModel() {

    val mainScreenViewState: StateFlow<MainScreenViewState> =
        MutableStateFlow(MainScreenViewState())

    /*
        init {
        // Fetch study spaces when ViewModel is initialized
        fetchStudySpaces()
        }


        private fun fetchStudySpaces() {
        viewModelScope.launch {
            try {
                val spaces = repository.getStudySpace() // Fetch data
                _mainScreenViewState.update { currentState ->
                    currentState.copy(studySpaces = spaces)
                }
            } catch (e: Exception) {
                // Handle errors (e.g., log or update UI state with error message)
                _mainScreenViewState.update { currentState ->
                    currentState.copy(studySpaces = listOf("Error loading study spaces"))
                }
            }
        }
        }

     */

}
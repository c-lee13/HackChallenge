package com.example.hackchallenge.ui.theme.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userName = MutableStateFlow(savedStateHandle["userName"] ?: "USER_NAME")
    val userName: StateFlow<String> get() = _userName

    var netId = mutableStateOf(savedStateHandle["netId"] ?: "NET_ID")
        private set
    var phoneNumber = mutableStateOf(savedStateHandle["phoneNumber"] ?: "1234567890")
        private set
    var email = mutableStateOf(savedStateHandle["email"] ?: "user@example.com")
        private set
    var country = mutableStateOf(savedStateHandle["country"] ?: "Country")
        private set

    var tempPhoneNumber = mutableStateOf(phoneNumber.value) // save temporal phone number input
    var tempEmail = mutableStateOf(email.value) // save temporal email input

    var isEditing = mutableStateOf(false)
    var showErrorDialog = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    // 更新 userName 并同步到 SavedStateHandle

    fun updateUserName(newName: String) {
        _userName.value = newName
        savedStateHandle["userName"] = newName
    }



    fun updateNetId(newNetId: String) {
        netId.value = newNetId
        savedStateHandle["netId"] = newNetId
    }

    fun commitChanges() {
        if (!isValidPhone(tempPhoneNumber.value)) {
            errorMessage.value = "Invalid phone number. Please enter a valid number."
            showErrorDialog.value = true
            tempPhoneNumber.value = phoneNumber.value // Reset
        } else if (!isValidEmail(tempEmail.value)) {
            errorMessage.value = "Invalid email address. Please enter a valid email."
            showErrorDialog.value = true
            tempEmail.value = email.value // Reset
        } else {
            phoneNumber.value = tempPhoneNumber.value
            email.value = tempEmail.value
            savedStateHandle["phoneNumber"] = phoneNumber.value
            savedStateHandle["email"] = email.value
        }
    }

    private fun isValidPhone(phone: String): Boolean {
        val phonePattern = "^[0-9]{10,15}$"
        return Pattern.matches(phonePattern, phone)
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        return Pattern.matches(emailPattern, email)
    }
}

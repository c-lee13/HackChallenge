package com.example.hackchallenge.ui.theme.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class ProfileViewModel : ViewModel() {
    var userName = mutableStateOf("USER_NAME")
    var netId = mutableStateOf("NET_ID")
    var phoneNumber = mutableStateOf("1234567890")
    var email = mutableStateOf("user@example.com")
    var country = mutableStateOf("Country")

    var tempPhoneNumber = mutableStateOf(phoneNumber.value) // save temporal phone number input
    var tempEmail = mutableStateOf(email.value) // save temporal email input

    var isEditing = mutableStateOf(false)
    var showErrorDialog = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    // if phone number and email is valid, commit the change
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
            // Save changes if valid
            startEditing()
        }
    }

    fun startEditing() {
        phoneNumber.value = tempPhoneNumber.value
        email.value = tempEmail.value
    }


    // check if phone number contains only digits & single line TODO check for length
    private fun isValidPhone(phone: String): Boolean {
        val phonePattern = "^[0-9]{10,15}$" // 10 to 15 digits
        return Pattern.matches(phonePattern, phone)
    }

    // check valid email pattern
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS.pattern()
        return Pattern.matches(emailPattern, email)
    }
}

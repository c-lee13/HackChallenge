package com.example.hackchallenge.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.collectAsState


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val userName = viewModel.userName.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
        )

        // Editable Fields

        ProfileField("User Name", userName, viewModel.isEditing.value) { newName ->
            viewModel.updateUserName(newName)
        }
        ProfileField("Net ID", viewModel.netId.value, viewModel.isEditing.value) { newNetId ->
            viewModel.updateNetId(newNetId)
        }
        ProfileField("Phone Number", viewModel.tempPhoneNumber.value, viewModel.isEditing.value) { viewModel.tempPhoneNumber.value = it }
        ProfileField("Email", viewModel.tempEmail.value, viewModel.isEditing.value) { viewModel.tempEmail.value = it }
        ProfileField("Country", viewModel.country.value, viewModel.isEditing.value) { viewModel.country.value = it }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.isEditing.value = !viewModel.isEditing.value
            },
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (viewModel.isEditing.value) Color.Green else Color.Blue
            )
        ) {
            Text(
                text = if (viewModel.isEditing.value) "Finish Editing" else "Edit Profile",
                color = Color.White
            )
        }
    }

    if (viewModel.showErrorDialog.value) {
        AlertDialog(
            onDismissRequest = { viewModel.showErrorDialog.value = false },
            title = { Text("Error") },
            text = { Text(viewModel.errorMessage.value) },
            confirmButton = {
                Button(onClick = { viewModel.showErrorDialog.value = false }) {
                    Text("OK")
                }
            }
        )
    }
}


@Composable
fun ProfileField(label: String, value: String, isEditing: Boolean, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "$label:",
            style = TextStyle(fontSize = 18.sp, color = Color.Gray)
        )
        if (isEditing) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        } else {
            Text(
                text = value,
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

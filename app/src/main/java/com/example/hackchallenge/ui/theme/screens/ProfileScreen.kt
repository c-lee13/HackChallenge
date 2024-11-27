package com.example.hackchallenge.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    CreateProfileLayout()
}

@Composable
fun CreateProfileLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        //TODO ensure that user first creates account (but idk how to do password)
        //TODO allow user to select own profile image
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .fillMaxWidth()
                .size(75.dp)
                .align(Alignment.CenterHorizontally)
        )


        Text(
            text = "Name: USER_NAME" //TODO allow user to select own username
        )

        Text(
            text = "Year: YEAR"
        )

        Text(
            text = "Preferences:"
        )

        Button(
            onClick = {},
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(
                text = "Edit Profile",
                color = Color.Black
            )
        }
    }
}
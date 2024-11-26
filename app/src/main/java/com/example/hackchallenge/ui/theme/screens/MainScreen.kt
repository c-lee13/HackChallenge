package com.example.hackchallenge.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackchallenge.R
import com.example.hackchallenge.data.StudySpace


@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val viewState = viewModel.mainScreenViewState.collectAsState().value
    createScreenLayout()

}

@Composable
public fun createScreenLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.Start
    ) {

        //menu button
        IconButton(
            onClick = {
                //TODO
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu button",
                tint = Color.Gray
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hey, INSERT_NAME!",
                fontSize = 30.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Text(
                text = "Looking to lock in?",
                fontSize = 30.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Text(
                text = "Check out these study spots!",
                fontSize = 30.sp,
                lineHeight = 35.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        //implement lazy row if have time

        val lazyListState = rememberLazyListState()

        val studySpacesList = listOf(
            StudySpace("RPCC", "Moderate", painterResource(id = R.drawable.rpcc), null),
            StudySpace("Appel", "Noisy", painterResource(id = R.drawable.mann), null),
            StudySpace("Mann Library", "Quiet", painterResource(id = R.drawable.rpcc), null),
            StudySpace("Uris Library", "Quiet", painterResource(id = R.drawable.rpcc), null),
            StudySpace("Morrison Dining", "Noisy", painterResource(id = R.drawable.rpcc), null),
        ) //TODO switch to API of study spaces later

        //TODO: lazy column for study spaces
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(2f)
        ) {
            items(studySpacesList) { space ->
                renderSpace(true, space.name, space.photo)
            }
        }
    }
}

@Composable
private fun renderSpace(isOpen: Boolean, name: String, photo: Painter?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp) //fixed height
            .padding(15.dp)
            .background(Color.Gray)
            .clip(RoundedCornerShape(16.dp)), // Rounded corners
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {

        //TODO image not being rounded & sized correctly
        /*
        if (photo != null) {
            Image(
                painter = photo,
                contentDescription = "Study Space Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)) // Ensure the image has rounded corners
            )
        }

         */


        Text(
            text = name,
            fontSize = 25.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            if (isOpen) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Open",
                    tint = Color.Green
                )

                Text(
                    text = "Open Now",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Closed",
                    tint = Color.Red
                )

                Text(
                    text = "Closed",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }


    }
}
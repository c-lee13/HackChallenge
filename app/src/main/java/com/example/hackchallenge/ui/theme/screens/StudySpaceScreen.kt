package com.example.hackchallenge.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackchallenge.R
import com.example.hackchallenge.data.StudySpace
import androidx.compose.runtime.collectAsState

@Composable
fun StudySpaceScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    viewModel: StudySpaceScreenViewModel = hiltViewModel(),
    reservationViewModel: ReservationScreenViewModel = hiltViewModel()
) {
    val userName = profileViewModel.userName.collectAsState().value
    val viewState = viewModel.studySpaceScreenViewState.collectAsState().value

    val preferencesList = viewState.preferences
    val allAttributesList = viewState.allAttributes

    val studySpaceList = listOf(
        StudySpace("RPCC", "Moderate", painterResource(id = R.drawable.rpcc), null),
        StudySpace("Appel", "Noisy", painterResource(id = R.drawable.mann), null),
        StudySpace("Mann Library", "Quiet", painterResource(id = R.drawable.rpcc), null),
        StudySpace("Uris Library", "Quiet", painterResource(id = R.drawable.rpcc), null),
        StudySpace("Morrison Dining", "Noisy", painterResource(id = R.drawable.rpcc), null),
    ) //TODO switch to API of study spaces later

    CreateScreenLayout(
        studySpacesList = studySpaceList,
        preferencesList = preferencesList,
        allAttributesList = allAttributesList,
        viewModel = viewModel,
        reservationScreenViewModel = reservationViewModel,
        userName = userName // 传递 userName
    )
}

@Composable
private fun CreateLazyRow(
    preferencesList: List<String>,
    allAttributesList: List<String>,
    viewModel: StudySpaceScreenViewModel
) {
    val lazyListState = rememberLazyListState()
    val otherList = allAttributesList.filter { it !in preferencesList }.distinct()

    LazyRow(
        state = lazyListState
    ) {
        items(preferencesList) { item ->
            Button(
                onClick = { viewModel.removePreference(item) }, //remove from preferences
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = item)
            }
        }

        //distinction between preferences & other attributes

        items(otherList) { item ->
            Button(
                onClick = { viewModel.addPreference(item) }, //remove from preferences
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun CreateScreenLayout(
    studySpacesList: List<StudySpace>,
    preferencesList: List<String>,
    allAttributesList: List<String>,
    viewModel: StudySpaceScreenViewModel,
    reservationScreenViewModel: ReservationScreenViewModel,
    userName: String
) {
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
                text = "Hey, $userName!",
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

        CreateLazyRow(preferencesList, allAttributesList, viewModel)

        val lazyListState = rememberLazyListState()

        //TODO: lazy column for study spaces
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(2f)
        ) {
            items(studySpacesList) { space ->
                RenderSpace(true, space, reservationScreenViewModel)
            }
        }
    }
}

@Composable
private fun RenderSpace(
    isOpen: Boolean,
    space: StudySpace,
    reservationViewModel: ReservationScreenViewModel
) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .clipToBounds(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {

        IconButton(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
        ) {
            if (space.photo != null) {
                Image(
                    painter = space.photo,
                    contentDescription = "Photo of study space",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Text(
                    text = "No Image Available"
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = space.name,
                fontSize = 25.sp
            )

            Row() {
                if (isOpen) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Open",
                        tint = Color.Green,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Text(
                        text = "Open Now"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Closed",
                        tint = Color.Red,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Text(
                        text = "Closed"
                    )
                }
            }

            //TODO reservations not updating when we go to reservation screen, seems like using different view model objects
            IconButton(
                onClick = { reservationViewModel.addReservation(space) }
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add to Reservations"
                )
            }
        }

    }
}
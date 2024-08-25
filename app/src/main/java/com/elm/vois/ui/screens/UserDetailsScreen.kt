package com.elm.vois.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.elm.vois.data.models.UserDetails
import com.elm.vois.ui.components.DetailsScreenWidgets.UserDetailsBody
import com.elm.vois.ui.viewModel.UserDetailsViewModel
import okhttp3.internal.wait

@Composable
fun UserDetailsScreen(viewModel: UserDetailsViewModel, username: String) {
    LaunchedEffect(username) {
        viewModel.fetchUserDetails(username)
    }

    val userDetails by viewModel.userDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            errorMessage != null -> {
                Text(
                    text = "Error: $errorMessage",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
            userDetails != null -> {
                UserDetailsBody(userDetails = userDetails!!)
            }
        }
    }
}





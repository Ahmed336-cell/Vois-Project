package com.elm.vois.ui.components.DetailsScreenWidgets

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.elm.vois.data.models.UserDetails


@Composable
fun UserDetailsBody(userDetails: UserDetails) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = userDetails.avatar_url),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = userDetails.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "@${userDetails.login}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bio",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Text(
            text = userDetails.bio ?: "No bio available",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomRow(label = "Company", value = userDetails.company ?: "Not Available")
        CustomRowClick(label = "Blog", value = userDetails.blog ?: "Not Available") {
            userDetails.blog?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                context.startActivity(intent)
            }
        }
        CustomRow(label = "Location", value = userDetails.location.toString() ?: "Not Available")
        CustomRow(label = "Followers", value = userDetails.followers.toString())
        CustomRow(label = "Following", value = userDetails.following.toString())
        CustomRow(label = "Public Repos", value = userDetails.public_repos.toString())
        CustomRow(label = "Public Gists", value = userDetails.public_gists.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(userDetails.html_url))
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text("View GitHub Profile", color = Color.Black)
        }
    }
}
package com.elm.vois.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elm.vois.R
import com.elm.vois.ui.components.UserScreenWidgets.UserList
import com.elm.vois.ui.viewModel.UserViewModel

@Composable
fun UsersScreen(viewModel: UserViewModel, onUserClick: (String) -> Unit) {
    val query = remember { mutableStateOf("") }
    val users by viewModel.users.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)


    ) {
        Row {
            Image(painterResource(id = R.drawable.github) ,
                contentDescription =null ,
                modifier = Modifier.size(70.dp)
                )

            Text(
                "GitHub Users",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            )
        }
        Spacer(modifier =Modifier.height(16.dp))
        Row {
            OutlinedTextField(
                textStyle = TextStyle(color = Color.White),
                value = query.value,
                onValueChange = { query.value = it },
                label = { Text("Search GitHub Users" , color = Color.White) },
                modifier = Modifier.width(250.dp)
                    .padding(start = 8.dp),
                leadingIcon = {
                    Image(
                        painterResource(id = R.drawable.github)
                        ,
                        contentDescription =null,
                        modifier = Modifier.size(24.dp),
                    )
                }
            )


            Spacer(modifier = Modifier.width(8.dp))

            Button(
                colors = ButtonDefaults.buttonColors(  Color.White),
                onClick = {
                    if (query.value.isNotEmpty()) {
                        viewModel.searchUsers(query.value)
                    }
                },
                modifier = Modifier.padding(top = 16.dp, end = 8.dp)
            ) {

                Text("Search" , color = Color.Black)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            errorMessage != null -> Text("Error: $errorMessage", color = Color.Red)
            else -> UserList(users = users,onUserClick=onUserClick)
        }
    }
}


package com.elm.vois.ui.components.UserScreenWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.elm.vois.data.models.User

@Composable
fun UserList(users: List<User>, onUserClick: (String) -> Unit) {
    LazyColumn (modifier = Modifier.background(color = Color.Black)) {
        items(users.size) { index ->
            UserItem(user = users[index] , onClick = {
                onUserClick(users[index].login)
            })
        }
    }
}

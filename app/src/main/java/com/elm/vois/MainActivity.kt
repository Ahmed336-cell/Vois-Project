package com.elm.vois

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elm.vois.ui.screens.UserDetailsScreen
import com.elm.vois.ui.screens.UsersScreen
import com.elm.vois.ui.theme.VoisTheme
import com.elm.vois.ui.viewModel.UserDetailsViewModel
import com.elm.vois.ui.viewModel.UserViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
        NavigationGraph(navController =navController)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
     val userViewModel: UserViewModel = viewModel()
    val detailsViewModel : UserDetailsViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Users.route
    ) {
        composable(Screen.Users.route) {
            UsersScreen(viewModel = userViewModel,
                onUserClick = { username ->
                    navController.navigate(Screen.UserDetails.createRoute(username))
                }
            )
        }
        composable(Screen.UserDetails.route) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            if (username != null) {
                UserDetailsScreen(viewModel=detailsViewModel,username = username)
            }
        }

    }
}
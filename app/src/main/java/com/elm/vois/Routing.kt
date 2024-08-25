package com.elm.vois

sealed class Screen(val route: String) {
  data object Users : Screen("users")
    data  object UserDetails : Screen("user_details/{username}") {
        fun createRoute(username: String) = "user_details/$username"
    }
}
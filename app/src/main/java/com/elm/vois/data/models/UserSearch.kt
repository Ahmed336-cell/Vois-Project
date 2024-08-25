package com.elm.vois.data.models

data class UserSearch(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)

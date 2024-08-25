package com.elm.vois.data.network


import com.elm.vois.data.models.UserDetails
import com.elm.vois.data.models.UserSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): Response<UserSearch>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<UserDetails>
}

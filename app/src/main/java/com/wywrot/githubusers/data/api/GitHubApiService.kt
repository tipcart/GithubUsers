package com.wywrot.githubusers.data.api

import com.wywrot.githubusers.data.model.User
import retrofit2.http.GET

interface GitHubApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}
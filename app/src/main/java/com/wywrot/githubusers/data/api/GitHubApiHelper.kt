package com.wywrot.githubusers.data.api

class GitHubApiHelper(private val apiService: GitHubApiService) {
    suspend fun getUsers() = apiService.getUsers()
}
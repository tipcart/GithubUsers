package com.wywrot.githubusers.data.repository

import com.wywrot.githubusers.data.api.GitHubApiHelper

class Repository(private val apiHelper: GitHubApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}
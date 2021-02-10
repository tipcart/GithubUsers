package com.wywrot.githubusers.data.repository

import com.wywrot.githubusers.data.GithubDatabase
import com.wywrot.githubusers.data.api.GitHubApiHelper
import com.wywrot.githubusers.data.model.User

class Repository(
    private val apiHelper: GitHubApiHelper,
    private val githubDatabase: GithubDatabase
) {

    suspend fun insertAllUser(users: List<User>) = githubDatabase.UserDAO().insertAll(users)

    suspend fun getUsers() = apiHelper.getUsers()
}
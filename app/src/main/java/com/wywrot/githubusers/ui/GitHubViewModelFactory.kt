package com.wywrot.githubusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wywrot.githubusers.data.api.GitHubApiHelper
import com.wywrot.githubusers.data.repository.Repository

class GitHubViewModelFactory(private val apiHelper: GitHubApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            return GitHubViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
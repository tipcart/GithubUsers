package com.wywrot.githubusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wywrot.githubusers.data.repository.Repository
import com.wywrot.githubusers.utils.Resource
import kotlinx.coroutines.Dispatchers

class GitHubViewModel(private val repository: Repository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading( null))
        try {
            emit(Resource.success(repository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error( null,  exception.message ?: "Error Occurred!"))
        }
    }
}
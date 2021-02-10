package com.wywrot.githubusers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wywrot.githubusers.data.model.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM github_user_entity WHERE id LIKE :id")
    fun getUserById(id: Int): User

    @Insert
    fun insertAll(users: List<User>)
}
package com.wywrot.githubusers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wywrot.githubusers.data.model.User

@Database(entities = [User::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun UserDAO(): UserDAO

    companion object {
        private var instance: GithubDatabase? = null

        private fun getInstance(context: Context) =
            Room.databaseBuilder(context, GithubDatabase::class.java, "github-users-db")
                .build()

        fun getDatabase(context: Context): GithubDatabase {
//             if (instance != null) {
//                instance
//            } else {
//                instance = getInstance(context)
//            }
//            return instance

            return getInstance(context)
        }

    }
}
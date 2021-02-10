package com.wywrot.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var htmlUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        userName = intent.getStringExtra("KEY_USER_NAME") ?: ""
        htmlUrl = intent.getStringExtra("KEY_URL") ?: ""

        setupUI()
    }

    private fun setupUI() {
        tvUserName.text = userName
        tvUserLink.text = htmlUrl
    }
}
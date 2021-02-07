package com.wywrot.githubusers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wywrot.githubusers.data.api.GitHubApiHelper
import com.wywrot.githubusers.data.api.RetrofitBuilder
import com.wywrot.githubusers.data.model.User
import com.wywrot.githubusers.ui.GitHubAdapter
import com.wywrot.githubusers.ui.GitHubViewModel
import com.wywrot.githubusers.ui.GitHubViewModelFactory
import com.wywrot.githubusers.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GitHubViewModel
    private lateinit var adapter: GitHubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, GitHubViewModelFactory(GitHubApiHelper(RetrofitBuilder.apiService))
        ).get(GitHubViewModel::class.java)
    }

    private fun setupUI() {
        setupSwipeRefresh()
        toolbar.title = getString(R.string.github_users)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GitHubAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(R.color.deep_orange_600)
        swipeRefresh.setOnRefreshListener {
            showFetchIndicator()
            setupObservers()
        }
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, {
            hideFetchIndicator()
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    private fun hideFetchIndicator() = swipeRefresh?.let {
        it.isRefreshing = false
        it.isEnabled = true
    }

    private fun showFetchIndicator() = swipeRefresh?.let { it.isRefreshing = true }
}
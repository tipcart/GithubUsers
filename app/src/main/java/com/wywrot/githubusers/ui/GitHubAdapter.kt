package com.wywrot.githubusers.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wywrot.githubusers.DetailsActivity
import com.wywrot.githubusers.R
import com.wywrot.githubusers.data.model.User
import kotlinx.android.synthetic.main.github_user_item.view.*

class GitHubAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<GitHubAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                tvAccountType.text = user.type
                tvUserName.text = user.login
                tvUserSiteUrl.text = user.htmlUrl
                Glide.with(ivAvatar.context)
                    .load(user.avatar)
                    .circleCrop()
                    .placeholder(R.drawable.ic_baseline_account_circle_24)
                    .into(ivAvatar)
                setOnClickListener {
                    val intent = Intent(itemView.context, DetailsActivity::class.java)
                    intent.putExtra("KEY_USER_NAME", user.login)
                    intent.putExtra("KEY_URL", user.htmlUrl)
                    startActivity(itemView.context, intent, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.github_user_item, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}

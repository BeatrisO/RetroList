package com.example.retrolist

import SimpleDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrolist.databinding.ItemPostBinding

class PostAdapter(private var posts: List<Post> = emptyList()) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }
    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.textTitle.text = post.title
        holder.binding.textBody.text = post.body
    }
    fun setPosts(newItems: List<Post>) {
        val diffResult = DiffUtil.calculateDiff(
            SimpleDiffCallback(posts, newItems) { old, new -> old.id == new.id }
        )
        posts = newItems
        diffResult.dispatchUpdatesTo(this)
    }
}


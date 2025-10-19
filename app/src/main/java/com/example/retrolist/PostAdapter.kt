package com.example.retrolist

import SimpleDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.retrolist.databinding.ItemPostBinding

class PostAdapter(
    private val onClick: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val posts = mutableListOf<Post>()

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.textPostTitle.text = post.title
            binding.textPostBody.text = post.body
            binding.root.setOnClickListener { onClick(post) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun setPosts(newList: List<Post>) {
        val diffCallback = SimpleDiffCallback(posts, newList) { old, new ->
            old.title == new.title
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        posts.clear()
        posts.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}

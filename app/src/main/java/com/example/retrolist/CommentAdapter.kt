package com.example.retrolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrolist.databinding.ItemCommentBinding
import org.w3c.dom.Comment

class CommentAdapter(private var comments: List<Comment> = emptyList()) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.binding.textCommentName.text = comment.name
        holder.binding.textCommentEmail.text = comment.email
        holder.binding.textCommentBody.text = comment.body
    }

    override fun getItemCount(): Int = comments.size

    fun setComments(newList: List<Comment>) {
        comments = newList
        notifyDataSetChanged()
    }
}

package com.example.retrolist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrolist.databinding.ActivityCommentListBinding

class CommentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentListBinding
    private val adapter = CommentAdapter()
    private val viewModel: CommentListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerComments.layoutManager = LinearLayoutManager(this)
        binding.recyclerComments.adapter = adapter

        val postId = intent.getIntExtra("POST_ID", -1)
        if (postId != -1) {
            viewModel.fetchComments(postId)
        } else {
            Toast.makeText(this, "Erro: ID do post inválido", Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.comments.observe(this) { comments ->
            adapter.setComments(comments)
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBarComments.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Erro ao carregar comentários", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

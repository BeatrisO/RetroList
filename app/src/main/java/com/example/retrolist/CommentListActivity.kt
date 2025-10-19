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
    private val viewModel: CommentListViewModel by viewModels()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val postId = intent.getIntExtra("POST_ID", 0)
        val postTitle = intent.getStringExtra("POST_TITLE") ?: "Comentários"
        supportActionBar?.title = postTitle

        commentAdapter = CommentAdapter()
        binding.recyclerComments.layoutManager = LinearLayoutManager(this)
        binding.recyclerComments.adapter = commentAdapter

        viewModel.comments.observe(this) { comments ->
            commentAdapter.setComments(comments)
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBarComments.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, "Erro ao carregar comentários: $it", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getComments(postId)
    }
}

package com.example.retrolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrolist.databinding.ActivityCommentListBinding

class CommentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.recyclerComments.layoutManager = LinearLayoutManager(this)

        binding.progressBarComments.visibility = android.view.View.VISIBLE

        binding.recyclerComments.postDelayed({
            binding.progressBarComments.visibility = android.view.View.GONE
        }, 1500)
    }
}

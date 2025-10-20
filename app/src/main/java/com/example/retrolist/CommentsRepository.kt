package com.example.retrolist

class CommentsRepository(private val api: ApiService = RetrofitClient.apiService) {

    suspend fun getComments(postId: Int): List<Comment> {
        return api.getComments(postId)
    }
}

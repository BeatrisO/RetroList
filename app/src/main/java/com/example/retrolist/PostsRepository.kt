package com.example.retrolist

class PostsRepository(private val api: ApiService = RetrofitClient.apiService) {
    suspend fun getPosts(): List<Post> = api.getPosts()
}
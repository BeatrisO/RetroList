package com.example.retrolist

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PostsViewModel(private val repo: PostsRepository = PostsRepository()) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    init {
        fetchPosts()
    }
    fun fetchPosts() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val list = repo.getPosts()
                _posts.value = list
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro desconhecido"
            } finally {
                _loading.value = false
            }
        }
    }
}
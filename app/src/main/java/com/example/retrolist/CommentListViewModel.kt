package com.example.retrolist

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CommentListViewModel(
    private val repository: CommentsRepository = CommentsRepository()
) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchComments(postId: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val response = repository.getComments(postId)
                _comments.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}

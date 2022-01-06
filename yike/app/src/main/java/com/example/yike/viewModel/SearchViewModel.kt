package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel(
    _questionKyWd: String
): ViewModel() {

    private val questionList = GlobalViewModel.getQuestionList(_questionKyWd)
    val questionKyWd = _questionKyWd
//    private val _isGet = MutableLiveData<Boolean>()
//    val isGet: LiveData<Boolean> = _isGet

    fun getQuestionList(): ArrayList<Question> {
        return questionList
    }
}
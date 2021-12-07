package com.example.yike.viewModel

import androidx.lifecycle.*
import com.example.yike.service.QuestionRepository

data class Question(val id: String, val title: String, val description: String,
                    val followNum: Int, val answerNum: Int)

class DiscussViewModel(): ViewModel() {

//    val questionList: MutableLiveData<ArrayList<Question>>? = null
//
//    val isLoading = true
//
//    init {
//        questionList = QuestionRepository.getQuestionList()
//        isLoading = false
//    }

    //观察对象：
    private val isInit = MutableLiveData<Boolean>(false)

    //界面变量
    val questionList = Transformations.switchMap(isInit) {
        QuestionRepository.getQuestionList()
    }

    //用户方法：
    fun init() {
        isInit.value = true
    }


//    private val _viewState = MutableStateFlow(DiscussViewState()) //可观测对象
//    val viewState: StateFlow<DiscussViewState> = _viewState //暴露
//
//    init {
//        fetchDiscussThemes()
////        fetchDiscussItems()
//    }
//
    //以主题的形式获取讨论
//    private fun fetchDiscussThemes() {
//        //协程
//        viewModelScope.launch {
//            //获取讨论
//            val discussThemes = discussRepository.fetchDiscussInThemes()
//
//            //导入到_viewState中
//            _viewState.value = _viewState.value.copy(
//                discussThemes = discussThemes,
//            )
//        }
//    }
//
//    //一一个个单项的形式获取讨论
//    private fun fetchDiscussItems() {
//        viewModelScope.launch {
//            val discussItems = discussRepository.fetchDiscussInItems()
//
//            _viewState.value = _viewState.value.copy(
//                discussItems = discussItems,
//            )
//        }
//    }
}
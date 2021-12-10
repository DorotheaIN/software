package com.example.yike.viewModel

import androidx.lifecycle.*
import com.example.yike.service.QuestionRepository

data class Question(val id: String, val title: String, val description: String,
                    val followNum: Int, val answerNum: Int)
data class QTheme(val id: String, val title: String, val description: String,
                  val followNum: Int, val answerNum: Int, val avatar: String)
//data class QTheme(val id: String, val title: String, val description: String,
//                  val followNum: Int, val answerNum: Int, val img: String)

//为了方便调用 使用了单例
//可以考虑引入ROOM之类的
class DiscussViewModel: ViewModel() {

    //观察对象：
    private val _isGet = MutableLiveData<Boolean>()

    //界面变量
    val questionList = Transformations.switchMap(_isGet) {
        QuestionRepository.getQuestionList()
    }
    val questionTheme = Transformations.switchMap(_isGet) {
        QuestionRepository.getQuestionByTheme()
    }
    val isGet: MutableLiveData<Boolean> = _isGet

    //用户方法：

    fun getQuestionList() {
        //同时请求item形式和theme形式的questionList
        _isGet.value = true
    }



//    fun getQuestionById(id: String) {
//        return
//    }


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
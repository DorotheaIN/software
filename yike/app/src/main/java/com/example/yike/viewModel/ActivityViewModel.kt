package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository



//data class Organization(
//    val username:String,
//    val avator:String,
//    val introduction:String
//)

data class Organization(
    val id: Int,
    val status:Int,
    val avator:String,
    val username:String,
    val introduction:String,
)

data class FilterInput(
    val genres: String,
    val status: String,
    val isAbleToRe:String,
    val key: String
)

//data class Activity(
//    val id:Int,
//    val title:String,
//    val organizer: Organization,
//    val img:String
//)

data class Activity(
    val title:String,
    val img:String,
    val date:String,
    val place:String,
    val form:String,
    val introduction:String,
    val content:String,
    val genres:String,
    var likeNum:Int,
    val capacity:Int,
    var status:Int,
    var subscriberNum:Int,
    val organizer: Organization,
    val id:Int,
)



class ActivityViewModel(): ViewModel() {

    private val _isGet = MutableLiveData<Boolean>()
    private val filterInput = MutableLiveData<FilterInput>()

    //界面变量
    val activityList = Transformations.switchMap(filterInput) {
        ActivityRepository.getActivityList(filterInput.value!!)
    }
    val isGet: MutableLiveData<Boolean> = _isGet
    //用户方法：
    fun getActivityList(){
        _isGet.value = true
        filterInput.value = FilterInput("","","","")
    }


    fun setGenres(genres: String){
        if(filterInput.value == null){
            filterInput.value = FilterInput(genres,"","","")
        }else {
            filterInput.value = FilterInput(genres, filterInput.value!!.status, filterInput.value!!.isAbleToRe,filterInput.value!!.key)
        }
    }

    fun setState(state: String){
        if(filterInput.value == null){
            filterInput.value = FilterInput("",state,"","")
        }else {
            filterInput.value = FilterInput(filterInput.value!!.genres, state, filterInput.value!!.isAbleToRe,filterInput.value!!.key)
        }
    }

    fun setSubState(subState: String){
        if(filterInput.value == null){
            filterInput.value = FilterInput("","",subState,"")
        }else {
            filterInput.value = FilterInput(filterInput.value!!.genres, filterInput.value!!.status, subState,filterInput.value!!.key)
        }
    }

    fun setTitle(title: String){
        if(filterInput.value == null){
            filterInput.value = FilterInput("","","",title)
        }else {
            filterInput.value = FilterInput(filterInput.value!!.genres, filterInput.value!!.status, filterInput.value!!.isAbleToRe,title)
        }
    }

}


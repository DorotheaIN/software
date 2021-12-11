package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.UserActivityRepository
import com.example.yike.viewModel.GlobalViewModel.getUserInfo


data class Evaluation(
    val reviewerID:Int,
    val activityID:Int,
    val content:String,
    val score:Int,
    val reviewerName:String,
    val reviewerIntroduction:String,
    val reviewerAvator:String
)


data class RelationInput(
    val activityID: Int,
    val userID:String,
    val status:Int = 1
)

data class UARelation(
    val likeStatus: Boolean,
    val subscribeStatus: Boolean
)

data class ActivityDetail(
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
    var subscriberNum:Int,/////////////
    val organizer: Organization,
    val id:Int,
)


class ActivityDetailViewModel(
    _activityId:Int
) :ViewModel(){
    private val activityID = _activityId
    private val userID = GlobalViewModel.getUserInfo()?.id
    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet


    private val toLike = MutableLiveData<Int>()

    private val toSub = MutableLiveData<Int>()

    val activityDetail = Transformations.switchMap(_isGet){
        ActivityRepository.getActivityDetail(activityID)
    }

    val evaluationList = Transformations.switchMap(_isGet){
        ActivityRepository.getEvaluationList(activityID)
    }

    val likeStatus = Transformations.switchMap(_isGet){
        UserActivityRepository.checkLike(activityID,userID!!)
    }

    val subscribeStatus = Transformations.switchMap(_isGet){
        UserActivityRepository.checkSubscribe(activityID,userID!!)
    }

    val activityRecommendedList = Transformations.switchMap(_isGet){it->
        ActivityRepository.getActivityRecommended(userID!!)
    }

    val likeRes = Transformations.switchMap(toLike){it->
        UserActivityRepository.postLikeActivity(activityID,userID!!,it)
    }

    val subRes = Transformations.switchMap(toSub){it->
        UserActivityRepository.postSubActivity(activityID,userID!!,it)
    }

    fun getActivityDetail(){
        _isGet.value = true
    }

    fun save(like:Boolean,subscribe:Boolean){
        println(like)
        println(subscribe)
        if(likeStatus.value != like && likeStatus!=null){
            if(like == true){//点赞
                toLike.value = 1
                UserActivityRepository.postLikeActivity(activityID,userID!!,1)
            }else{
                toLike.value = 0
                UserActivityRepository.postLikeActivity(activityID,userID!!,0)
            }
        }
        if(subscribeStatus.value != subscribe && subscribeStatus!=null){
            if(subscribe == true){
                toSub.value = 1
                UserActivityRepository.postSubActivity(activityID,userID!!,1)
            }else {
                toSub.value = 0
                UserActivityRepository.postSubActivity(activityID,userID!!,0)
            }
        }
    }


}
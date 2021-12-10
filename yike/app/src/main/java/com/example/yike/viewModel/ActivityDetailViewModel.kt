package com.example.yike.viewModel

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.UserActivityRepository
import com.example.yike.viewModel.GlobalViewModel.getUserInfo

//data class Evaluation(
//    val activityID:Int,
//    val reviewerID:Int,
//    val reviewerName:String,
//    val reviewerAvator:String,
//    val content:String,
//    val score:Int
//)

data class Evaluation(
    val reviewerID:Int,
    val activityID:Int,
    val content:String,
    val score:Int,
    val reviewerName:String,
    val reviewerIntroduction:String,
    val reviewerAvator:String
)

//data class ActivityDetail(
//    val id:Int,
//    val title:String,
//    val organizer: Organization,
//    val img:String,
//    val date:String,
//    val place:String,
//    val form:String,
//    val capacity:Int,
//    val introduction:String,
//    val content:String,
//    val genres:String,
//    var status:Int,
//    var likeNum:Int,
//    var subscriberNum:Int,
//)

data class RelationInput(
    val activityID: Int,
    val userID:String
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

data class ActivityID(val activityID: Int)

class ActivityDetailViewModel() :ViewModel(){
    //观察对象：
//    private val activityID = MutableLiveData<ActivityID>()
    private val id = MutableLiveData<Int>()

//    private val initialLikeStatus = MutableLiveData<Boolean>()
    private val relationInput = MutableLiveData<RelationInput>()

    val activityDetail = Transformations.switchMap(id){it->
        ActivityRepository.getActivityDetail(it)
    }

    val evaluationList = Transformations.switchMap(id){it->
        ActivityRepository.getEvaluationList(it)
    }

    val likeStatus = Transformations.switchMap(relationInput){it->
        UserActivityRepository.checkLike(it.activityID,it.userID)
    }

    val subscribeStatus = Transformations.switchMap(relationInput){it->
        UserActivityRepository.checkSubscribe(it.activityID,it.userID)
    }

    private val userID = MutableLiveData<String>()


    val activityRecommendedList = Transformations.switchMap(userID){it->
        ActivityRepository.getActivityRecommended(it)
    }


    fun save(like:Boolean,subscribe:Boolean){
        val userInfo = getUserInfo()
        if(userInfo != null){
            println(likeStatus.value)
            println(like)
            if(likeStatus.value != like && likeStatus!=null){
                if(like == true){//点赞
                    println("点赞")
                    println(id.value)
                    println(id.value != null)
                    if(id.value != null){
                        UserActivityRepository.postLikeActivity(id.value!!,userInfo.id)
                    }
//                    id.value?.let {  }
                    println(userInfo.id)
                }
            }
            if(subscribeStatus.value != subscribe && subscribeStatus!=null){

            }
        }
    }

    fun init(ID:Int){
        id.value = ID
        val userInfo = getUserInfo()
        if(userInfo != null){
            userID.value = userInfo.id
            relationInput.value = RelationInput(ID,userInfo.id)
        }
    }

    fun getRelation(){
        return
    }

}
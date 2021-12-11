package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.OrganizationRepository

data class EvaluationAnalysis(
    val cloud:String,
    val emo_ANALYSIS:String
)

data class Subscriber(
    val id:String,
    val status:Int,
    val avator:String,
    val user_NAME:String,
    val introduction:String
)

class ActivityReflectViewModel(
    _activityID:Int
):ViewModel(){
    private val activityID = _activityID
    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet

    val evaluationAnalysis = Transformations.switchMap(_isGet){
        OrganizationRepository.getEvaAnalysis(activityID)
    }

    val subscriberList = Transformations.switchMap(_isGet){
        OrganizationRepository.getSubscriberList(activityID)
    }

    fun getAll(){
        _isGet.value = true
    }
}
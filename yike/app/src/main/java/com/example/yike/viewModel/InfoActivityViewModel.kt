package com.example.yike.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.data.Organization
import com.example.yike.service.ActivityRepository
import com.example.yike.service.MyRepository


class InfoActivityViewModel(): ViewModel() {
    private val userInfo = GlobalViewModel.getUserInfo()
    private val _isGet = MutableLiveData<Boolean>()
    //    val isGet : LiveData<Boolean> = _isGet
    val isGet: MutableLiveData<Boolean> = _isGet

    val myActivities = Transformations.switchMap(_isGet){
        MyRepository.getMyActivities(userInfo!!.id)
    }

    fun getMyActivities(){
        _isGet.value = true
        println(userInfo!!.id)
    }
}


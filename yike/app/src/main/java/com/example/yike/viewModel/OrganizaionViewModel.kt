package com.example.yike.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.ActivityRepository
import com.example.yike.service.OrgLoginRepository
import com.example.yike.service.OrganizationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OrganizationViewModel(): ViewModel() {

    private val _isGet = MutableLiveData<Boolean>()
    val isGet: LiveData<Boolean> = _isGet

    private val _isDel = MutableLiveData<Int>()
    private val _refresh = MutableLiveData<Int>()
    val refresh : LiveData<Int> = _refresh

    //之后要改成从Global获取
    val organizationInfo = Transformations.switchMap(_isGet) {
        OrgLoginRepository.checkLoginStatus(3,"tongji_sse")
    }

    val activityList = Transformations.switchMap(_refresh){
        OrganizationRepository.getActivityByOrganization(3,)
    }

    val delRes = Transformations.switchMap(_isDel){
        OrganizationRepository.deleteActivity(_isDel.value!!)
    }

    fun getInfo(){
        _isGet.value = true
        _refresh.value = 0
    }

    fun delete(id:Int) = runBlocking {
        val del = launch {
            _isDel.value = id
            delay(250)
        }
        del.join()
        val ref = launch {
            _refresh.value = _refresh.value?.plus(1)
        }
    }




//    fun delete(id:Int){
//        _isDel.value = id
//        _refresh.value = _refresh.value?.plus(1)
//    }

}

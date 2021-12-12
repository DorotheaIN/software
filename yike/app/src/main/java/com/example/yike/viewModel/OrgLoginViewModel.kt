package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.OrgLoginRepository

data class OrgLoginInput(
    val id:Int,
    val password:String
)

class OrgLoginViewModel():ViewModel(){
    private val orgLoginInput = MutableLiveData<OrgLoginInput>()

    val orgInfo = Transformations.switchMap(orgLoginInput){it->
        OrgLoginRepository.checkLoginStatus(it.id,it.password)
    }

    fun checkLoginStatus(id:Int,password: String){
        orgLoginInput.value = OrgLoginInput(id,password)
    }
}
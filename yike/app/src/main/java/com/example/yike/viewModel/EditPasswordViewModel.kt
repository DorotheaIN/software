package com.example.yike.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.yike.service.UpdateRepository

data class InputEditInfo(
    val ID:String,
    val password:String
)

class EditPasswordViewModel ():ViewModel(){


    private val inputEditInfo = MutableLiveData<InputEditInfo>()

    val getEditInfo = Transformations.switchMap(inputEditInfo){inputEditInfo->
        UpdateRepository.editPassword(inputEditInfo.ID,inputEditInfo.password)
    }

    fun sendEditInfo(email:String,password: String){
        inputEditInfo.value = InputEditInfo(email,password)
    }
}
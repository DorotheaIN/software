package com.example.yike.viewModel

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.onimur.handlepathoz.HandlePathOz
import br.com.onimur.handlepathoz.HandlePathOzListener
import com.example.yike.service.OfficialRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.Exception
import java.net.URI


data class InputOfficialInfo(
    val avator:String,
    val certification:String,
    val introduction:String,
    val password:String,
    val userName:String,
)

class OfficialRegisterViewModel() :ViewModel(){
    private val inputOfficialInfo = MutableLiveData<InputOfficialInfo>()

    val officialRegisterInfo = Transformations.switchMap(inputOfficialInfo) {
        OfficialRepository.officialRegister(it.avator,it.certification,it.introduction,it.password,it.userName)
    }

    //用户方法
    fun checkOfficialRegisterStatus(imgUri:String,docUri:String,introduction:String,password:String,userName:String){
        println("!!!!!!!!!!!!!")
        println("send info!!!")
        inputOfficialInfo.value = InputOfficialInfo(imgUri, docUri, introduction,password,userName)
    }

}
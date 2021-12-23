package com.example.yike.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FreeTextDialog(
    isSuccess: MutableState<String>,
    openDialog: MutableState<Boolean>,
){
    if(openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(
                    text = isSuccess.value,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.h6
                )
            },
//                text = {
//                    Text(
//                        text = "组织用户账号申请已成功提交，请耐心等待管理员审核，最终审核结果将会通过邮箱通知您！",
//                        fontSize = 16.sp
//                    )
//                },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    },
                ) {
                    Text(
                        "确认",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.button
                    )
                }
            },
        )
    }
}

// 注册提示框
@Composable
fun OrgRegisterDialog(
    isSuccess: MutableState<Boolean>,
    openDialog: MutableState<Boolean>,
    clickEvent: ()-> Unit
){
    if(openDialog.value){
        if(isSuccess.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(
                        text = "注册成功",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.h6
                    )
                },
                text = {
                    Text(
                        text = "组织用户账号申请已成功提交，请耐心等待管理员审核，最终审核结果将会通过邮箱通知您！",
                        fontSize = 16.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            run{
                                clickEvent()
                            }
                        },
                    ) {
                        Text(
                            "确认",
                            fontWeight = FontWeight.W700,
                            style = MaterialTheme.typography.button
                        )
                    }
                },
            )
        } else {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(
                        text = "密码错误",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.h6
                    )
                },
                text = {
                    Text(
                        text = "组织用户账号申请已成功提交，请耐心等待管理员审核，最终审核结果将会通过邮箱通知您！",
                        fontSize = 16.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        },
                    ) {
                        Text(
                            "确认",
                            fontWeight = FontWeight.W700,
                            style = MaterialTheme.typography.button
                        )
                    }
                },
            )
        }
    }

}
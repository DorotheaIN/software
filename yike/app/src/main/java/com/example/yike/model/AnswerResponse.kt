package com.example.yike.model

import com.example.yike.viewModel.Answer

data class AnswerResponse(val code: Int, val msg: String, val result: ArrayList<Answer>, val dataCount: Int)
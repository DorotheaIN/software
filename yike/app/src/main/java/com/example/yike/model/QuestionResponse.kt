package com.example.yike.model

import com.example.yike.viewModel.Question


//{
//  "status": "ok",
//  "result|15": [{
//    "id": "1",
//    "title": "@title(3,5)",
//    "description": "@cparagraph(3,5)",
//    "followNum|1-100": 1,
//    "answerNum|1-100": 1,
//  }]
//
//}
data class QuestionResponse(val status: String, val result: ArrayList<Question>)
//

package com.example.yike

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.yike.viewModel.GlobalViewModel

class CommentInputState : TextFieldState(
    validator = ::isValidComment,
    errorFor = ::commentValidationError
) {

}

private fun commentValidationError(password: String): String {
    return "超过20字."
}

private fun isValidComment(comment: String): Boolean {
    return comment.length <= 20
}
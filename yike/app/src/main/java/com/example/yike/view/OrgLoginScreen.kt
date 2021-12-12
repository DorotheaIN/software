package com.example.yike.view


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yike.EmailState
import com.example.yike.PasswordInputState
import com.example.yike.component.PrimaryButton
import com.example.yike.component.RequiredInputState
import com.example.yike.viewModel.GlobalViewModel
import com.example.yike.viewModel.OrgLoginViewModel
import com.example.yike.viewModel.Organization

@Composable
fun OrgLoginScreen(
    viewModel: OrgLoginViewModel,
    routeEvent:()->Unit = {}
){
    val orgInfo = viewModel.orgInfo.observeAsState()
    LoginContent(orgInfo = orgInfo.value, routeEvent){ id, password ->
        viewModel.checkLoginStatus(id,password)
    }
}

@Composable
private fun LoginContent(
    orgInfo:Organization?,
    routeEvent:()->Unit = {},
    clickEvent:(id:Int,password:String) -> Unit
){
    if(orgInfo != null) {
        println(orgInfo)
        GlobalViewModel.updateOrgInfo(orgInfo)
        run(routeEvent)
    } else {
        println(orgInfo)
    }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val passwordInput = remember { PasswordInputState() }
        val idInput = remember { RequiredInputState() }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {

//            MyExample()

            LogInHeader()

            IdInput(idInput)

            Spacer(Modifier.height(8.dp))

            PasswordInput(passwordInput)


            TermsOfServiceLabel()

            Spacer(Modifier.height(16.dp))


            LoginButton( onClick = {
                if( idInput.isValid && passwordInput.isValid ){
                    run{
                        println(idInput.text + passwordInput.text)
                        clickEvent(idInput.text.toInt(), passwordInput.text)
                    }
                }
            })
        }
    }
}


@Composable
private fun LoginButton( onClick: () -> Unit) {
    PrimaryButton(
        buttonText = "Log in",
        onClick = onClick
    )
}

@Composable
private fun TermsOfServiceLabel() {
    Text(
        text = "By clicking below you agree to our Terms of Use and consent to our Privacy Policy.",
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp),
    )
}

@Composable
private fun PasswordInput(passwordInput: PasswordInputState) {
    val textState = remember {
        PasswordInputState()
    }
    OutlinedTextField(
        value = textState.text,
        onValueChange = { newText ->
            textState.text = newText
            passwordInput.text = textState.text
        },
        label = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
        ),
        visualTransformation = if (textState.shouldHidePassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                val isFocused = it.isFocused
                textState.onFocusChange(isFocused)

                if (!isFocused) {
                    textState.enableShowErrors()
                }
            },
        isError = textState.showErrors,
        trailingIcon = {
            Crossfade(targetState = textState.shouldHidePassword) { hidePassword ->
                if (hidePassword) {
                    PasswordVisabilityIcon(
                        iconToUse = Icons.Default.VisibilityOff,
                        textState = textState
                    )
                } else {
                    PasswordVisabilityIcon(
                        iconToUse = Icons.Default.Visibility,
                        textState = textState
                    )
                }
            }
        }
    )

    textState.getError()?.let { errorMessage ->
        TextFieldError(textError = errorMessage)
    }
}

@Composable
private fun PasswordVisabilityIcon(
    iconToUse: ImageVector,
    textState: PasswordInputState
) {
    Icon(
        iconToUse,
        contentDescription = "Toggle Password Visibility",
        modifier = Modifier
            .clickable {
                textState.shouldHidePassword = !textState.shouldHidePassword
            },
    )
}

@Composable
private fun IdInput(idInput: RequiredInputState) {
    val textState = remember {
        RequiredInputState()
    }
    OutlinedTextField(
        value = textState.text,
        onValueChange = { newString ->
            textState.text = newString
            idInput.text = textState.text
        },
        label = {
            Text(text = "Organization ID")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { it ->
                val isFocused = it.isFocused
                textState.onFocusChange(isFocused)
                if (!isFocused) {
                    textState.enableShowErrors()
                }
            },
        isError = textState.showErrors,
    )

    textState.getError()?.let { errorMessage ->
        TextFieldError(textError = errorMessage)
    }
}

@Composable
private fun TextFieldError(textError: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            style = LocalTextStyle.current.copy(color = MaterialTheme.colors.error)
        )
    }
}

@Composable
private fun LogInHeader() {
    Text(
        text = "Log in",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 16.dp,
            )
    )
}
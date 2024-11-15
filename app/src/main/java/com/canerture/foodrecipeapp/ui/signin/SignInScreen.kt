package com.canerture.foodrecipeapp.ui.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canerture.foodrecipeapp.common.collectWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignInScreen(
    uiState: SignInContract.UiState,
    uiEffect: Flow<SignInContract.UiEffect>,
    onUiAction: (SignInContract.UiAction) -> Unit,
    navigateToHome: () -> Unit,
) {
    val context = LocalContext.current
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is SignInContract.UiEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            SignInContract.UiEffect.NavigateToHome -> navigateToHome()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 48.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign In",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email,
            onValueChange = { onUiAction(SignInContract.UiAction.EmailChanged(it)) },
            label = { Text("Email") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password,
            onValueChange = { onUiAction(SignInContract.UiAction.PasswordChanged(it)) },
            label = { Text("Password") }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onUiAction(SignInContract.UiAction.SignInClicked) },
        ) {
            Text("Sign In")
        }

        Text(
            text = "or",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onUiAction(SignInContract.UiAction.GoogleSignInClicked) },
        ) {
            Text("Sign In with Google")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        uiState = SignInContract.UiState(),
        uiEffect = emptyFlow(),
        onUiAction = {},
        navigateToHome = {},
    )
}
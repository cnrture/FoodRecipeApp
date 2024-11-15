package com.canerture.foodrecipeapp.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.foodrecipeapp.ui.signin.SignInContract.UiAction
import com.canerture.foodrecipeapp.ui.signin.SignInContract.UiEffect
import com.canerture.foodrecipeapp.ui.signin.SignInContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.EmailChanged -> updateUiState { copy(email = uiAction.email) }
            is UiAction.PasswordChanged -> updateUiState { copy(password = uiAction.password) }
            is UiAction.SignInClicked -> signIn()
            is UiAction.GoogleSignInClicked -> {}
        }
    }

    private fun signIn() {
        updateUiState { copy(isLoading = true) }
        viewModelScope.launch {
            emitUiEffect(UiEffect.ShowToast("Sign In Clicked"))
        }
        updateUiState { copy(isLoading = false) }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
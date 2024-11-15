package com.canerture.foodrecipeapp.ui.signin

object SignInContract {
    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
    )

    sealed interface UiAction {
        data class EmailChanged(val email: String) : UiAction
        data class PasswordChanged(val password: String) : UiAction
        data object SignInClicked : UiAction
        data object GoogleSignInClicked : UiAction
    }

    sealed class UiEffect {
        data object NavigateToHome : UiEffect()
        data class ShowToast(val message: String) : UiEffect()
    }
}
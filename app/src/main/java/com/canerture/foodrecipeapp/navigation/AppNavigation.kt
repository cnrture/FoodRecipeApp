package com.canerture.foodrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.canerture.foodrecipeapp.ui.signin.SignInScreen
import com.canerture.foodrecipeapp.ui.signin.SignInViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "signIn"
    ) {
        composable("signIn") {
            val viewModel = hiltViewModel<SignInViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignInScreen(
                uiState = uiState.value,
                uiEffect = uiEffect,
                onUiAction = viewModel::onAction,
                navigateToHome = { navHostController.navigate("home") }
            )
        }

        // Sign Up Screen
        // Home Screen
        // Recipe Detail Screen
        // Profile Screen
    }
}
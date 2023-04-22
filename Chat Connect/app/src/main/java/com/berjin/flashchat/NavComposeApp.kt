package com.berjin.flashchat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.berjin.flashchat.nav.Action
import com.berjin.flashchat.nav.Destination.AuthenticationOption
import com.berjin.flashchat.nav.Destination.Home
import com.berjin.flashchat.nav.Destination.Login
import com.berjin.flashchat.nav.Destination.Register
import com.berjin.flashchat.ui.theme.FlashChatTheme
import com.berjin.flashchat.view.AuthenticationView
import com.berjin.flashchat.view.home.HomeView
import com.berjin.flashchat.view.login.LoginView
import com.berjin.flashchat.view.register.RegisterView


@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    FlashChatTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView()
            }
        }
    }
}
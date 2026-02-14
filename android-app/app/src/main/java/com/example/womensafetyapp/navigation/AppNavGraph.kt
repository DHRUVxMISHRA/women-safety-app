package com.example.womensafetyapp.navigation

import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.womensafetyapp.service.SOSForegroundService
import com.example.womensafetyapp.ui.auth.LoginScreen
import com.example.womensafetyapp.ui.auth.SignupScreen
import com.example.womensafetyapp.ui.chat.ChatScreen
import com.example.womensafetyapp.ui.home.HomeScreen
import com.example.womensafetyapp.ui.onboarding.GetStartedScreen
import com.example.womensafetyapp.ui.profile.ProfileScreen
import com.example.womensafetyapp.ui.sos.EmergencyScreen
import android.Manifest

import android.os.Build


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Routes.GET_STARTED
    ){

        composable(Routes.GET_STARTED){
            GetStartedScreen(
                onGetStartedClick = {
                    navController.navigate(Routes.LOGIN){

                            popUpTo(Routes.GET_STARTED) { inclusive = true }

                    }
                }
            )
        }

        composable(Routes.LOGIN){
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.HOME){
                        popUpTo(Routes.GET_STARTED)
                        {
                            inclusive = true
                        }
                    }
                },
                onSignupClick = {
                    navController.navigate(Routes.SIGNUP)
                }
            )
        }

        composable(Routes.SIGNUP){
            SignupScreen(
                onSignupClick = {
                    navController.navigate(Routes.HOME){
                        popUpTo(Routes.GET_STARTED)
                        {
                            inclusive = true
                        }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME){
            val context = LocalContext.current


            HomeScreen(
                onSosClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // request notification permission if needed
                    }

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        val serviceIntent =
                            Intent(context, SOSForegroundService::class.java)

                        context.startForegroundService(serviceIntent)

                        navController.navigate(Routes.EMERGENCY){
                            launchSingleTop = true
                        }

                    }

                },
                onChatClick = {
                    navController.navigate(Routes.CHAT)
                }
            )
        }

        composable(Routes.EMERGENCY){

            val context = LocalContext.current
            EmergencyScreen(
                locationText = "Fetching Location...",
                onStopClick = {
                    // 🛑 Stop foreground service
                    val serviceIntent = Intent(context, SOSForegroundService::class.java)
                    context.stopService(serviceIntent)
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.CHAT){
            ChatScreen(userId = "demo_user_123")
        }

        composable(Routes.PROFILE){
            ProfileScreen()
        }
    }

}
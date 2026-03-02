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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.womensafetyapp.MyViewModel.SOSViewModel
import com.example.womensafetyapp.settings.SettingsScreen

import com.example.womensafetyapp.ui.auth.AuthState
import com.example.womensafetyapp.ui.auth.AuthViewModel
import com.example.womensafetyapp.ui.auth.OtpScreen
import com.example.womensafetyapp.ui.auth.PhoneNumberScreen
import com.example.womensafetyapp.ui.helpline.HelplineScreen
import com.example.womensafetyapp.ui.profile.Profile
import com.example.womensafetyapp.ui.sos.SOSScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val authViewModel : AuthViewModel = viewModel()

    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        when(authState){
            is AuthState.Unauthenticated -> {
                navController.navigate(Routes.GET_STARTED){
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }

            is AuthState.PhoneVerificationRequired -> {
                navController.navigate(Routes.PHONE_NUMBER)
            }

            is AuthState.OtpSent -> {
                navController.navigate(Routes.OTP)
            }

            is AuthState.Authenticated -> {
                navController.navigate(Routes.PROFILE){
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }

            is AuthState.GetStarted  -> {
                navController.navigate(Routes.GET_STARTED)
            }

            else -> Unit
        }
    }

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
               authViewModel = authViewModel,
                navController = navController
            )
        }

        composable(Routes.SIGNUP){
            SignupScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Routes.PHONE_NUMBER){

            PhoneNumberScreen(
                navController = navController,
                authViewModel = authViewModel
            )

        }

        composable(Routes.OTP){
            OtpScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

//        composable(Routes.AADHAAR){
//            AadhaarScreen(navController)
//        }
//
//        composable(Routes.HOME){
//            val context = LocalContext.current


//            HomeScreen(
//                onSosStart = {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                        // request notification permission if needed
//                    }
//
//                    if (ContextCompat.checkSelfPermission(
//                            context,
//                            Manifest.permission.ACCESS_FINE_LOCATION
//                        ) != PackageManager.PERMISSION_GRANTED
//                    ) {
//
//                        // Request permission
//                        ActivityCompat.requestPermissions(
//                            context as android.app.Activity,
//                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                            100
//                        )
//
//                        return@HomeScreen
//                    }
//
//                    val serviceIntent =
//                        Intent(context, SOSForegroundService::class.java)
//
//                    context.startForegroundService(serviceIntent)
//
//                    navController.navigate(Routes.EMERGENCY) {
//                        launchSingleTop = true
//                    }
//
//                },
//                onSosStop = {
//
//                },
//                onChatClick = {
//                    navController.navigate(Routes.CHAT)
//                },
//                onLogoutClick = {
//                    authViewModel.signOut()
//                }
//            )
//        }

        composable(Routes.EMERGENCY){

            val context = LocalContext.current
            val viewModel: SOSViewModel = hiltViewModel()
            val locationText by viewModel.locationText.collectAsState()

            EmergencyScreen(
                locationText = locationText,
                onStopClick = {
                    // 🛑 Stop foreground service
                    val serviceIntent = Intent(context, SOSForegroundService::class.java)
                    context.stopService(serviceIntent)
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.CHAT){
            ChatScreen(userId = 500)
        }

        composable(Routes.PROFILE_SCREEN){
            ProfileScreen(
//                navController = navController,
                onClick = {
                    authViewModel.signOut()

                }
            )
        }

        composable(Routes.PROFILE){
            Profile(
                onTrackMeClick = {
                    navController.navigate(Routes.SAFE_ROUTE)
                },

                onSOSClick = {
                    navController.navigate(Routes.SOS)
                },

                onSettingClick = {
                    navController.navigate(Routes.SETTINGS)
                },

                onChatClick = {
                    navController.navigate(Routes.CHAT)
                },

                onHelplineClick = {
                    navController.navigate(Routes.HELPLINE)
                }
            )
        }

        composable(Routes.SAFE_ROUTE){
            SafeRouteScreen()
        }

        composable(Routes.SOS){

            val context = LocalContext.current
            val viewModel: SOSViewModel = hiltViewModel()
            val locationText by viewModel.locationText.collectAsState()

            SOSScreen(
                locationText = locationText,
                onStartSOS = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // request notification permission if needed
                    }

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        // Request permission
                        ActivityCompat.requestPermissions(
                            context as android.app.Activity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            100
                        )

                        return@SOSScreen
                    }

                    val serviceIntent =
                        Intent(context, SOSForegroundService::class.java)

                    context.startForegroundService(serviceIntent)

                },
                onStopSOS = {

                    val serviceIntent =
                        Intent(context, SOSForegroundService::class.java)

                    context.stopService(serviceIntent)
                }
            )
        }

        composable(Routes.SETTINGS){
            SettingsScreen(
                onLogoutClick = {
                    authViewModel.signOut()
                }
            )
        }

        composable(Routes.HELPLINE){
            HelplineScreen()
        }

    }

}
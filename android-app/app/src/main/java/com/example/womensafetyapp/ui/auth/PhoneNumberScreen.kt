package com.example.womensafetyapp.ui.auth

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.womensafetyapp.navigation.Routes

@Composable
fun PhoneNumberScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    var phone by remember { mutableStateOf("") }
    var context = LocalContext.current
    var activity = context as Activity


    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {

        when (authState) {

            is AuthState.OtpSent -> {
                navController.navigate(Routes.OTP)
            }

            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Verify Your Phone Number",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                    phone = it
                }
            },

            label = {
                Text(
                    text = "Enter 10-digit mobile number"
                )
            },

            prefix = {
                Text(
                    text = "+91"
                )
            },

            singleLine = true,

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (phone.length == 10) {

                    authViewModel.setPhoneNumber("+91$phone")
                    authViewModel.sendOtp("+91$phone", activity)

                } else {

                    Toast.makeText(context, "Enter a valid 10-digit number", Toast.LENGTH_SHORT).show()

                }
            },

            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text("Send OTP")
        }
    }

}
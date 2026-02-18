package com.example.womensafetyapp.ui.auth

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.womensafetyapp.R
import com.example.womensafetyapp.ui.theme.Poppins
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.womensafetyapp.navigation.Routes
import okhttp3.Route
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.example.womensafetyapp.utils.Constants
import kotlin.contracts.contract

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit,
    navController : NavHostController,
    authViewModel: AuthViewModel)
{

   var name by remember { mutableStateOf("") }
   var addharNumber by remember { mutableStateOf("") }
   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   var confirmPassword by remember { mutableStateOf("") }

   val context = LocalContext.current

    val authState by authViewModel.authState.observeAsState()
    val passwordStrength by authViewModel.passwordStrength.observeAsState()


    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(Constants.WEB_CLIENT_ID)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            val emailFromGoogle = account.email

            if (emailFromGoogle != null && idToken != null){
                authViewModel.googleSignup(emailFromGoogle, idToken)
            }
        } catch (e : Exception){
            Toast.makeText(context, "Google Sign-Up Failed", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(authState) {
        when(authState){
            is AuthState.Authenticated ->{
                navController.navigate(Routes.HOME)
            }

            is AuthState.PhoneVerificationRequired ->{
                navController.navigate(Routes.PHONE_NUMBER){
                    popUpTo(Routes.SIGNUP){
                        inclusive = false
                    }
                }
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFFFFFFFF), // 0% White
//                      0.7f to Color(0xFFFF0099).copy(alpha = 0.7f) // 75% Pink
                        0.75f to Color(0xFFFF66B3)
                    )
                ),
//                Color.White.copy(alpha = 0.1f)
            )
    ){
        Image(
            painter = painterResource(R.drawable.shape),
            contentDescription = "circles",
            modifier = Modifier
                .size(900.dp)
                .align(alignment = Alignment.BottomCenter)
                .offset(x = (-10).dp, y = 300.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(120.dp))


            Text(
                text = "Welcome Onbaord!",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                lineHeight = 20.826.sp,
                letterSpacing = 1.08.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()


            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Secure Verified, Encrypted, Because Your Data Matters",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 17.836.sp,
                letterSpacing = 0.78.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text("Enter your full name")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
//                        .shadow(
//                            elevation = 20.dp,
//                            shape = RoundedCornerShape(50.dp),
//                            ambientColor = Color.Black.copy(alpha = 0.15f),
//                            spotColor = Color.Black.copy(alpha = 0.15f)
//                        )
                        .graphicsLayer{
                            shadowElevation = 45.dp.toPx()
                            shape = RoundedCornerShape(50.dp)
                            clip = false

                        }


                )
            Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = addharNumber,
                    onValueChange = {
                        addharNumber = it
                    },
                    label = {
                        Text("Enter your addhar number")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
//                        .shadow(
//                            elevation = 12.dp,
//                            shape = RoundedCornerShape(50.dp),
//                            ambientColor = Color.Black.copy(alpha = 0.15f),
//                            spotColor = Color.Black.copy(alpha = 0.15f)
//                        )
                        .graphicsLayer{
                            shadowElevation = 45.dp.toPx()
                            shape = RoundedCornerShape(50.dp)
                            clip = false
                        }
                )

            Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text("Enter your email")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
//                        .shadow(
//                            elevation = 12.dp,
//                            shape = RoundedCornerShape(50.dp),
//                            ambientColor = Color.Black.copy(alpha = 0.15f),
//                            spotColor = Color.Black.copy(alpha = 0.15f)
//                        )
                        .graphicsLayer{
                            shadowElevation = 45.dp.toPx()
                            shape = RoundedCornerShape(50.dp)
                            clip = false
                        },
                    isError =authState is AuthState.Error && (authState as AuthState.Error).message.contains("email", true)
                )

            if(authState is AuthState.Error && (authState as AuthState.Error).message.contains("email", true)){

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = (authState as AuthState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize =12.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val passwordBorderColor = when(passwordStrength){

                PasswordStrength.WEAK -> Color(0xFFB00020)
                PasswordStrength.MEDIUM -> Color(0xFFFFC107)
                PasswordStrength.STRONG-> Color(0xFF4CAF50)

                else -> Color.White.copy(alpha = 0.3f)
//
            }
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        authViewModel.onPasswordChanged(it)
                    },
                    label = {
                        Text("Enter your password")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor =Color.White.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedBorderColor = passwordBorderColor,
                        unfocusedBorderColor = passwordBorderColor,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
//                        .shadow(
//                            elevation = 12.dp,
//                            shape = RoundedCornerShape(50.dp),
//                            ambientColor = Color.Black.copy(alpha = 0.15f),
//                            spotColor = Color.Black.copy(alpha = 0.15f)
//                        )
                        .graphicsLayer{
                            shadowElevation = 45.dp.toPx()
                            shape = RoundedCornerShape(50.dp)
                            clip = false
                        },

                    isError = authState is AuthState.Error && (authState as AuthState.Error).message.contains("password", true)
                )

            passwordStrength?.let { strength ->

                Spacer(modifier = Modifier.height(4.dp))
                val(text , color) = when(strength){
                    PasswordStrength.WEAK ->
                        "Weak password" to Color(0xFFB00020)

                    PasswordStrength.MEDIUM ->
                        "Medium strength password" to Color(0xFFFFC107)

                    PasswordStrength.STRONG ->
                        "Strong password" to Color(0xFF4CAF50)
                }

                Text(
                    text = text,
                    color = color,
                    fontSize =14.sp,
                    fontWeight = FontWeight.Bold,

                    )

//                    textStyle = TextStyle.


            }
            Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                    },
                    label = {
                        Text("Confirm password")
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.3f),
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
//                        .shadow(
//                            elevation = 12.dp,
//                            shape = RoundedCornerShape(50.dp),
//                            ambientColor = Color.Black.copy(alpha = 0.15f),
//                            spotColor = Color.Black.copy(alpha = 0.15f)
//                        )
                        .graphicsLayer{
                            shadowElevation = 45.dp.toPx()
                            shape = RoundedCornerShape(50.dp)
                            clip = false
                        }
                )




            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                 authViewModel.signup(email, password)
                },
                enabled = email.isNotBlank() && password.length>=6 && authState != AuthState.Loading && passwordStrength != PasswordStrength.WEAK,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF0099)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
//                    .shadow(
//                        elevation = 20.dp,
//                        shape = RoundedCornerShape(50.dp),
//                        ambientColor = Color.Black.copy(alpha = 0.25f),
//                        spotColor = Color.Black.copy(alpha = 0.25f)
//                    )
                    .graphicsLayer {
                        shadowElevation = 50.dp.toPx()
                        shape = RoundedCornerShape(50.dp)
                        clip = false
                    }

            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }


            Button(
                onClick = {
                    launcher.launch(googleSignInClient.signInIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(50.dp)
            ) {

                Text("Sign Up with Google")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){

                Text(
                    text = "Already have an account? ",
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = Poppins
                )

                    Text(
                        text = "Sign In",
                        fontFamily = Poppins,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        modifier = Modifier.clickable{
                            onLoginClick()
                        }

                    )

            }


        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun SignupScreenPreview(modifier: Modifier = Modifier) {
//    SignupScreen(
//        onSignupClick = {},
//        onLoginClick = {}
//    )
}
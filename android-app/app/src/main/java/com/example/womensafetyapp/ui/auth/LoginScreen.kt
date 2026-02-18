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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.womensafetyapp.R
import com.example.womensafetyapp.navigation.Routes
import com.example.womensafetyapp.ui.theme.Poppins
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.example.womensafetyapp.utils.Constants



@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    authViewModel: AuthViewModel,
    navController : NavHostController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()

    val context = LocalContext.current

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(Constants.WEB_CLIENT_ID)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context,gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken

            idToken?.let {
                authViewModel.firebaseAuthWithGoogle(it)
            }
        } catch (e : ApiException){
            Toast.makeText(context, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> onLoginClick()
            is AuthState.PhoneVerificationRequired -> {
                navController.navigate(Routes.PHONE_NUMBER)
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
//            .padding((-10).dp)

    ) {


        Image(
            painter = painterResource(R.drawable.shape),
            contentDescription = "null",
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomStart)
                .size(800.dp)
                .offset(x = (-20).dp, y = 300.dp),
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
                text = "Welcome Back!",
                fontSize = 22.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.836.sp,
                letterSpacing = 1.08.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Image(
                painter = painterResource(R.drawable.loginpage2),
                contentDescription = "null"
            )

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "Enter your email"
                    )
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
                    .graphicsLayer {
                        shadowElevation = 45.dp.toPx()
                        shape = RoundedCornerShape(50.dp)
                        clip = false
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(
                        text = "Enter password"
                    )
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
                    .graphicsLayer {
                        shadowElevation = 45.dp.toPx()
                        shape = RoundedCornerShape(50.dp)
                        clip = false
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {

                }
            ) {
                Text(
                    text = "Forgot Password",
                    fontFamily = Poppins,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,

                    )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF0099)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)

                    .graphicsLayer {
                        shadowElevation = 50.dp.toPx()
                        shape = RoundedCornerShape(50.dp)
                        clip = false
                    }
            ) {
                Text(
                    text = "Login",
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
                Text("Continue with Google")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Don't have an account? ",
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = Poppins
                )

                Text(
                    text = "Sign Up",
                    fontFamily = Poppins,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                     navController.navigate(Routes.SIGNUP)
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
fun LoginScreenPreview(modifier: Modifier = Modifier) {
//    LoginScreen(
//        onLoginClick = {},
//        onSignupClick = {},
//        authViewModel = a
//    )

}
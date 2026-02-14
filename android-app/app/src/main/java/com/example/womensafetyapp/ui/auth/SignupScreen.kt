package com.example.womensafetyapp.ui.auth

import android.content.res.Configuration
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.womensafetyapp.R
import com.example.womensafetyapp.ui.theme.Poppins
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit)
{

   var name by remember { mutableStateOf("") }
   var addharNumber by remember { mutableStateOf("") }
   var email by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   var confirmPassword by remember { mutableStateOf("") }

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
                        }
                )

            Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text("Enter your password")
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
                onClick = onSignupClick,
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
    SignupScreen(
        onSignupClick = {},
        onLoginClick = {}
    )
}
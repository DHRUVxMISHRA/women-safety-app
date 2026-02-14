package com.example.womensafetyapp.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.womensafetyapp.R
import com.example.womensafetyapp.ui.theme.Poppins

@Composable
fun GetStartedScreen(
    onGetStartedClick:()->Unit
) {

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
            painter = painterResource(R.drawable.circle2),
            contentDescription = "circle",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 24.dp, bottom = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.height(120.dp))




            // 🖼 Illustration
            Image(
                painter = painterResource(id = R.drawable.ic_illustration2),
                contentDescription = "illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Your Safety, Our Priority",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 20.8.sp, // 18 * 1.157
                letterSpacing = 1.08.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Our app ensures your safety with instant SOS alerts, real-time tracking, and emergency communication.",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 17.836.sp,
                letterSpacing = 0.78.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = onGetStartedClick,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF0099)
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(62.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(50.dp),
                        ambientColor = Color(0xFFFF0099),
                        spotColor = Color(0xFFFF0099)),

                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 12.dp,
                    pressedElevation = 6.dp
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))



        }
    }

    
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewGetStaratedScreen(modifier: Modifier = Modifier) {
    GetStartedScreen(
        onGetStartedClick = {

        }
    )
}



package com.example.womensafetyapp.ui.home

import android.R
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
   onSosClick:()-> Unit,
    onChatClick: ()-> Unit,
    onLogoutClick: ()-> Unit


) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "You are Safe",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )

        Button(
            onClick = onSosClick,
            modifier = Modifier
                .size(220.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = "SOS",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Text(
            text = "Tap in case of emergency",
            fontSize = 14.sp,
            color = Color.Gray
        )


        Button(
            onClick = onChatClick,
            modifier = Modifier.size(180.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = "Talk to Sakhi",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Text(
            text = "Logout",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { onLogoutClick() }
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        onSosClick = {},
        onChatClick = {},
        onLogoutClick = {}
    )
}
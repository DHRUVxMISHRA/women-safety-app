package com.example.womensafetyapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun Profile(modifier: Modifier = Modifier) {

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
            // Avatar
            Image(
                painter = painterResource(R.drawable.profile_avatar), // add your avatar image
                contentDescription = "Profile",
                modifier = Modifier
                    .height(110.dp)
            )

// Name
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Isha Sharma",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))


            ProfileItem(R.drawable.ic_sos, "SOS")
//            ProfileItem(R.drawable.ic_track, "Track me")
//            ProfileItem(R.drawable.ic_helpline, "Helpline")
//            ProfileItem(R.drawable.ic_record, "Record")
//            ProfileItem(R.drawable.ic_sms, "SMS")
//            ProfileItem(R.drawable.ic_support, "App Support")
//            ProfileItem(R.drawable.ic_settings, "Settings")

        }
    }

}

@Composable
fun ProfileItem(
    icon: Int,
    title: String,
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(60.dp)
            .shadow(6.dp, RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8D6E4)
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.height(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

//            Image(
//                painter = painterResource(R.drawable.ic_arrow), // right arrow icon
//                contentDescription = "arrow",
//                modifier = Modifier.height(20.dp)
//            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview(modifier: Modifier = Modifier) {
Profile()

}
package com.example.womensafetyapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun Profile(
    onTrackMeClick : () -> Unit
) {

    Scaffold(
        bottomBar = {
           BottomNavigationBar()
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
        ) {

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
                    .verticalScroll(rememberScrollState())
                    .padding(top = 20.dp, start = 24.dp, bottom = 24.dp, end = 24.dp),
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


                ProfileItem(R.drawable.ic_sos6, "SOS")
                ProfileItem(R.drawable.ic_track6, "Track me", onClick = onTrackMeClick)
                ProfileItem(R.drawable.ic_helpline6, "Helpline")
                ProfileItem(R.drawable.ic_record6, "Record")
                ProfileItem(R.drawable.ic_sms6, "SMS")
                ProfileItem(R.drawable.ic_support6, "App Support")
                ProfileItem(R.drawable.ic_settings6, "Settings")

            }
        }
    }
}


@Composable
fun ProfileItem(
    icon: Int,
    title: String,
    onClick: () -> Unit = {}
) {
    androidx.compose.material3.Card(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFFF8D6E4)
        ),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)   // lock the height
                .padding(vertical = 8.dp, horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(R.drawable.arrowright),
                contentDescription = "arrow",
                modifier = Modifier.height(18.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview(modifier: Modifier = Modifier) {
Profile(
    onTrackMeClick = {

    }
)

}

@Composable
fun BottomNavigationBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp), // more height to allow floating
        contentAlignment = Alignment.BottomCenter
    ) {

        // Floating Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) // side gap
                .height(75.dp)
                .offset(y = (-25).dp) // 🔥 move up from bottom
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(30.dp)
                )
                .clip(RoundedCornerShape(30.dp))
                .background(
                    Color(0xFFFFE6F2).copy(alpha = 0.9f) // soft blend color
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(horizontal = 40.dp)
                .offset(y = (-12).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            BottomItem(R.drawable.ic_home6, "Home")
            BottomItem(R.drawable.ic_updates6, "Updates")

            Spacer(modifier = Modifier.width(50.dp))

            BottomItem(R.drawable.ic_community6, "Communities")
            BottomItem(R.drawable.ic_profile6, "Profile")
        }

        // Floating SOS Button
        Box(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-30).dp)
                .shadow(25.dp, CircleShape)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFF4F8B),
                            Color(0xFFE91E63)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SOS",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun BottomItem(
    icon: Int,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(y = (-13).dp)
    ) {

        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(26.dp)
        )

        Text(
            text = label,
            fontSize = 11.sp,
            color = Color.Black
        )
    }
}
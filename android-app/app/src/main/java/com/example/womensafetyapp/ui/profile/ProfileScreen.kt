package com.example.womensafetyapp.ui.profile

import android.content.res.Configuration
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.womensafetyapp.navigation.Routes
import com.example.womensafetyapp.ui.auth.AuthViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onClick :() -> Unit,
//    navController : NavHostController
) {


    var bloodGroup by remember { mutableStateOf("") }
    var emergencyContact by remember { mutableStateOf("") }
    val authViewModel : AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Medical Information",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bloodGroup,
            onValueChange = {
                bloodGroup = it
            },
            label = {
                Text(
                    text = "Blood Group"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = emergencyContact,
            onValueChange = {
                emergencyContact = it
            },
            label = {
                Text(
                    text = "Emergency Contact"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
           onClick = onClick
        ) {
            Text("Logout")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun ProfileScreenPreview(modifier: Modifier = Modifier) {
//    ProfileScreen()

}
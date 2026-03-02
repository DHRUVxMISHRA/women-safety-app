package com.example.womensafetyapp.ui.helpline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.womensafetyapp.models.SpContact
import com.example.womensafetyapp.models.spContacts
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource

@Composable
fun HelplineScreen() {

    var searchQuery by remember { mutableStateOf("") }

    val sortedList = spContacts.sortedBy { it.district }

    val filteredList = sortedList.filter {
        it.district.contains(searchQuery, ignoreCase = true) ||
                it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color(0xFFFF66B3)
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))   // 👈 shifted search downward

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search district...") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp)),

            singleLine = true,
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE91E63),
                unfocusedBorderColor = Color(0xFFE91E63)
            ),
            shape = RoundedCornerShape(30.dp)

        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(filteredList) { contact ->
                SpContactCard(contact)
            }
        }
    }
}

@Composable
fun SpContactCard(contact: SpContact) {

    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8D6E4)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { expanded = !expanded }
            ) {
                Text(
                    text = contact.district.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = if (expanded) "▲" else "▼",
                    fontSize = 16.sp
                )
            }

            AnimatedVisibility(visible = expanded) {

                Column {   // 👈 THIS FIXES OVERLAP

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("SP: ${contact.name}")

                    Spacer(modifier = Modifier.height(6.dp))

                    Text("Office: ${contact.office}")

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Mobile: ${contact.mobile}",
                        color = Color(0xFF1565C0),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${contact.mobile}")
                            }
                            context.startActivity(intent)
                        }
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Email: ${contact.email}",
                        color = Color(0xFF1565C0),
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:${contact.email}")
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}
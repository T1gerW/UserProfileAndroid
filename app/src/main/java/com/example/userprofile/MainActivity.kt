package com.example.userprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userprofile.ui.theme.UserProfileTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserProfileTheme {
                // Call the UserProfileScreen composable instead of Greeting
                UserProfileScreen()
            }
        }
    }
}

@Composable
fun UserProfileScreen() {
    // Create a snackbar host state and coroutine scope for showing the snackbar.
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Scaffold provides a layout structure that includes a Snackbar host.
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        // Use a Column to vertically arrange the profile elements.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),  // Outer padding for screen content.
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Display the profile picture.
            Image(
                painter = painterResource(id = R.drawable.lebron), // Ensure this resource exists.
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)  // Clip the image into a circular shape.
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Wrap the username in a Row for alignment.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Lebron James",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Display the bio.
            Text(
                text = "Basketball Player",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // The "Follow" button. When clicked, it launches a coroutine to display the snackbar.
            Button(onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Following")
                }
            }) {
                Text(text = "Follow")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileTheme {
        UserProfileScreen()
    }
}

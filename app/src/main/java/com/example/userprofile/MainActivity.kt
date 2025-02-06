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
                UserProfileScreen()
            }
        }
    }
}

@Composable
fun UserProfileScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.lebron),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Text(
                text = "Basketball Player",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
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

package com.openclassroom.joiefull.compositions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.R

/**
 * The composable function of the screen composition to display the no internet screen.
 */
@Composable
fun NoInternetScreen() {
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.tertiary)) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(0.dp)
        ) {
            Box(modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize()
                .padding(it)
            ){
                Column (modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_signal_wifi_connected_no_internet_4_24),
                        contentDescription = stringResource(R.string.no_internet_content_description),
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .height(70.dp)
                            .width(70.dp)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = stringResource(R.string.no_internet_title),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.white),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(40.dp,0.dp,40.dp,0.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.no_internet_description),
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.white),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(40.dp,0.dp,40.dp,0.dp)
                    )
                }
            }
        }
    }
}


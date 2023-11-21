package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@DevicePreviews
@Composable
fun ArtSpaceLayout() {
    @DrawableRes
    var imageResource by remember { mutableStateOf(R.drawable.sample_1) }
    var artWorkTitle by remember { mutableStateOf("Sample 1") }
    var artist by remember { mutableStateOf("Owen Scott") }
    var releaseYear by remember { mutableStateOf("2011") }
    var curPos by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        ArtWorkImage(
            imageResource = imageResource,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .border(BorderStroke(35.dp, MaterialTheme.colorScheme.surface))
                .fillMaxWidth(0.7f)
                .weight(3f)
        )

        Spacer(modifier = Modifier.height(40.dp))

        ArtWorkDescriptor(
            artWorkTitle = artWorkTitle, artist = artist, releaseYear = releaseYear,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 8.dp, vertical = 20.dp)
                .fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        DisplayController(
            modifier = Modifier
                .fillMaxWidth(),
            onPrevClick = {
                when (curPos) {
                    1 -> {
                        imageResource = R.drawable.sample_3
                        artWorkTitle = "Sample 3"
                        artist = "Alex Antony"
                        releaseYear = "2023"
                        curPos = 3
                    }

                    2 -> {
                        imageResource = R.drawable.sample_1
                        artWorkTitle = "Sample 1"
                        artist = "Owen Scott"
                        releaseYear = "2011"
                        curPos = 1
                    }

                    3 -> {
                        imageResource = R.drawable.sample_2
                        artWorkTitle = "Sample 2"
                        artist = "Sam"
                        releaseYear = "2020"
                        curPos = 2
                    }
                }
            },
            onNextClick = {
                when (curPos) {
                    1 -> {
                        imageResource = R.drawable.sample_2
                        artWorkTitle = "Sample 2"
                        artist = "Sam"
                        releaseYear = "2020"
                        curPos = 2
                    }

                    2 -> {
                        imageResource = R.drawable.sample_3
                        artWorkTitle = "Sample 3"
                        artist = "Alex Antony"
                        releaseYear = "2023"
                        curPos = 3
                    }

                    3 -> {
                        imageResource = R.drawable.sample_1
                        artWorkTitle = "Sample 1"
                        artist = "Owen Scott"
                        releaseYear = "2011"
                        curPos = 1
                    }
                }
            }
        )
    }
}

@Composable
fun ArtWorkImage(
    @DrawableRes imageResource: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 12.dp,
        modifier = modifier
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = null, contentScale = ContentScale.Fit)
    }
}

@Composable
fun ArtWorkDescriptor(
    artWorkTitle: String,
    artist: String,
    releaseYear: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = artWorkTitle, style = MaterialTheme.typography.titleLarge)
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(artist)
                }
                append(" ($releaseYear)")
            }
        )
    }
}

@Composable
fun DisplayController(
    modifier: Modifier = Modifier,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Button(
            onClick = onPrevClick,
            modifier = Modifier
                .width(120.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .width(120.dp)
        ) {
            Text(text = "Next")
        }
    }
}




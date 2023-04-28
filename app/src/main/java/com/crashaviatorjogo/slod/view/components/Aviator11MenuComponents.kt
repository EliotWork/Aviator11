package com.crashaviatorjogo.slod.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.crashaviatorjogo.slod.R

@Composable
fun Aviator11MenuBg(appBackground: Int) {
    Image(painter = painterResource(id = appBackground), contentDescription = "",
        Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
    Image(painter = painterResource(id = R.drawable.background03), contentDescription = "",
        Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
}
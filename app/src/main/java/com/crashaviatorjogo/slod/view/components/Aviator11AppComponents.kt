package com.crashaviatorjogo.slod.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.view.ui.theme.Typography

@Composable
fun Aviator11AppBg(appBackground: Int) {
    Image(painter = painterResource(id = appBackground), contentDescription = "",
        Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
}

@Composable
fun Aviator11AppMenu(aviator11navigateToMenu: () -> Unit) {
    Box(Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Image(painter = painterResource(id = R.drawable.menu01), contentDescription = "",
            Modifier.clickable(onClick = aviator11navigateToMenu), contentScale = ContentScale.FillBounds)
    }
}

@Composable
fun Aviator11Coins(aviator11Coins: Int) {
    Box(Modifier.fillMaxSize().padding(top = 15.dp), contentAlignment = Alignment.TopCenter) {
        Box(contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.coins), contentDescription = "")
            Text(text = aviator11Coins.toString(), Modifier.padding(start = 20.dp), style = Typography.body2)
        }
    }
}
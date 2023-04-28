package com.crashaviatorjogo.slod.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.view.components.Aviator11AppBg
import com.crashaviatorjogo.slod.view.components.aviator11QuitConstraint
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11MenuViewModel
import kotlin.system.exitProcess

@Composable
fun Aviator11QuitScreen(viewModel: Aviator11MenuViewModel) {
    Aviator11AppBg(viewModel.aviator11Background)
    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))
    ConstraintLayout(aviator11QuitConstraint(), Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.text_box01), contentDescription = "",
            Modifier.layoutId("av11qBg"))
        Image(painter = painterResource(id = R.drawable.text_quit), contentDescription = "",
            Modifier.layoutId("av11qTitle"))
        Image(painter = painterResource(id = R.drawable.ok), contentDescription = "",
            Modifier.layoutId("av11qOk").clickable { exitProcess(-1) })
        Image(painter = painterResource(id = R.drawable.close), contentDescription = "",
            Modifier.layoutId("av11qClose").clickable { viewModel.aviator11QuitScreenVisibility = false })
        Text(text = stringResource(id = R.string.quit_question).uppercase(),
            Modifier.layoutId("av11qContent"), style = Typography.h3, textAlign = TextAlign.Center)
    }
}
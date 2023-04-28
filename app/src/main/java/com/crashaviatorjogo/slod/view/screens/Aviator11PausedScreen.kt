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
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.view.components.aviator11PausedConstraint
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11PolygonViewModel

@Composable
fun Aviator11PausedScreen(
    navHostController: NavHostController,
    viewModel: Aviator11PolygonViewModel
) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))

    ConstraintLayout(aviator11PausedConstraint(), Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.text_box01), contentDescription = "",
            Modifier.layoutId("av11pBg"))
        Image(painter = painterResource(id = R.drawable.text_paused), contentDescription = "",
            Modifier.layoutId("av11pTitle"))
        Image(painter = painterResource(id = R.drawable.restart), contentDescription = "",
            Modifier.layoutId("av11pRestart").clickable { navHostController.navigate("Aviator11ResumePolygon") })
        Image(painter = painterResource(id = R.drawable.menu01), contentDescription = "",
            Modifier.layoutId("av11pMenu").clickable { navHostController.popBackStack("Aviator11MenuScreen", false) })
        Image(painter = painterResource(id = R.drawable.play), contentDescription = "",
            Modifier.layoutId("av11pResume").clickable { viewModel.aviator11PauseFlight() })
        Text(text = stringResource(id = R.string.paused_question).uppercase(),
            Modifier.layoutId("av11pContent"), style = Typography.h3, textAlign = TextAlign.Center)
    }
}
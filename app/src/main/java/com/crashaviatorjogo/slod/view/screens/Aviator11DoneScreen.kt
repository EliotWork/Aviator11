package com.crashaviatorjogo.slod.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.crashaviatorjogo.slod.R

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.view.components.aviator11DoneConstraint
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11PolygonViewModel

@Composable
fun Aviator11DoneScreen(navHostController: NavHostController, viewModel: Aviator11PolygonViewModel) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))

    ConstraintLayout(aviator11DoneConstraint(), Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.text_box01), contentDescription = "",
            Modifier.layoutId("av11dBg"))
        Image(painter = painterResource(id = R.drawable.text_done), contentDescription = "",
            Modifier.layoutId("av11dTitle"))
        Image(painter = painterResource(id = R.drawable.restart), contentDescription = "",
            Modifier.layoutId("av11dRestart")
                .clickable { navHostController.navigate("Aviator11ResumePolygon") })
        Image(painter = painterResource(id = R.drawable.menu01), contentDescription = "",
            Modifier.layoutId("av11dMenu")
                .clickable { navHostController.popBackStack("Aviator11MenuScreen", false) })
        Column(Modifier.layoutId("av11dContent").width(120.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(Modifier.width(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.score).uppercase(), style = Typography.h3)
                Text(text = "${viewModel.aviator11Score}", style = Typography.h4)
            }
            Row(Modifier.width(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.best).uppercase(), style = Typography.h3)
                Text(text = "${viewModel.aviator11BestRecord}", style = Typography.h4)
            }
            Row(Modifier.width(120.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(Modifier.width(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(id = R.string.coins).uppercase(), style = Typography.h3)
                    Text(text = "+${viewModel.aviator11PolygonCoins}", style = Typography.h4)
                }
                Image(painter = painterResource(id = R.drawable.coin), contentDescription = "",
                    Modifier.size(15.dp).padding(start = 5.dp))
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.aviator11DoneScreenAction()
    }
}
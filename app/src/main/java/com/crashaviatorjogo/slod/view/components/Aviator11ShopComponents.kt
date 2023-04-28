package com.crashaviatorjogo.slod.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.view.ui.theme.Typography

@Composable
fun Aviator11ShopPrice(modifier: Modifier) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.price), contentDescription = "")
        Text(text = "100", Modifier.padding(end = 10.dp), style = Typography.body1, color = Color.Black)
    }
}

@Composable
fun aviator11ShopConstraint(): ConstraintSet {
    return ConstraintSet {
        val av11sBg = createRefFor("av11sBg")
        val av11sClose = createRefFor("av11sClose")
        val av11sApply = createRefFor("av11sApply")
        val av11sTitle = createRefFor("av11sTitle")
        val av11sContent = createRefFor("av11sContent")

        constrain(av11sBg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(av11sClose) {
            top.linkTo(av11sBg.top, margin = 20.dp)
            end.linkTo(av11sBg.end)
            start.linkTo(av11sBg.end)
        }
        constrain(av11sApply) {
            start.linkTo(av11sBg.start)
            end.linkTo(av11sBg.end)
            top.linkTo(av11sBg.bottom, margin = 10.dp)
        }
        constrain(av11sTitle) {
            start.linkTo(parent.start, margin = 25.dp)
            top.linkTo(av11sBg.top, margin = 10.dp)
        }
        constrain(av11sContent) {
            start.linkTo(av11sBg.start)
            end.linkTo(av11sBg.end)
            top.linkTo(av11sTitle.bottom)
            bottom.linkTo(av11sBg.bottom, margin = 14.dp)
        }
    }
}
package com.crashaviatorjogo.slod.view.components

import androidx.compose.animation.core.LinearEasing
import com.crashaviatorjogo.slod.R
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.crashaviatorjogo.slod.data.Aviator11OffsetXList
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11PolygonViewModel

@Composable
fun Aviator11PolygonItems(viewModel: Aviator11PolygonViewModel) {
    val aviator11PolygonHeight = LocalConfiguration.current.screenHeightDp.dp
    val aviator11Coin1Y by aviator11YAnimation(viewModel.aviator11XCoin1Yanim) { viewModel.aviator11Coin1FinishListener() }
    val aviator11Coin2Y by aviator11YAnimation(viewModel.aviator11XCoin2Yanim) { viewModel.aviator11Coin2FinishListener() }
    val aviator11Coin3Y by aviator11YAnimation(viewModel.aviator11XCoin3Yanim) { viewModel.aviator11Coin3FinishListener() }
    val aviator11CoinsY by aviator11YAnimation(viewModel.aviator11XCoinsYanim) { viewModel.aviator11CoinsFinishListener() }
    val aviator11Barrier1Y by aviator11YAnimation(viewModel.aviator11XBarrier1Yanim) { viewModel.aviator11Barrier1FinishListener() }
    val aviator11Barrier2Y by aviator11YAnimation(viewModel.aviator11XBarrier2Yanim) { viewModel.aviator11Barrier2FinishListener() }
    val aviator11Barrier3Y by aviator11YAnimation(viewModel.aviator11XBarrier3Yanim) { viewModel.aviator11Barrier3FinishListener() }
    val aviator11Barrier4Y by aviator11YAnimation(viewModel.aviator11XBarrier4Yanim) { viewModel.aviator11Barrier4FinishListener() }

    Box(Modifier
        .fillMaxSize()
        .padding(bottom = aviator11PolygonHeight / 4),
        contentAlignment = Alignment.BottomCenter
    ) {
        /* Plane */
        Image(painter = painterResource(id = viewModel.aviator11Plane), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11PlaneAlpha)
                .offset { IntOffset(Aviator11OffsetXList[viewModel.aviator11PlaneX], 0) })

        /* Polygon Items */
        Image(painter = painterResource(id = R.drawable.coin), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XCoin1Yalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XCoin1X],
                        aviator11Coin1Y)
                })

        Image(painter = painterResource(id = R.drawable.coin), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XCoin2Yalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XCoin2X],
                        aviator11Coin2Y)
                })

        Image(painter = painterResource(id = R.drawable.coin), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XCoin3Yalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XCoin3X],
                        aviator11Coin3Y)
                })

        Image(painter = painterResource(id = R.drawable.coins_three), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XCoinsYalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XCoinsX],
                        aviator11CoinsY)
                })

        Image(painter = painterResource(id = R.drawable.barrier01), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XBarrier1Yalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XBarrier1X],
                        aviator11Barrier1Y)
                })

        Image(painter = painterResource(id = R.drawable.barrier02), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XBarrier2Yalpha)
                .offset {
                    IntOffset(Aviator11OffsetXList[viewModel.aviator11XBarrier2X],
                        aviator11Barrier2Y)
                })

        Image(painter = painterResource(id = R.drawable.barrier03), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XBarrier3Yalpha)
                .offset {
                    IntOffset(
                        Aviator11OffsetXList[viewModel.aviator11XBarrier3X],
                        aviator11Barrier3Y
                    )
                })

        Image(painter = painterResource(id = R.drawable.barrier04), contentDescription = "",
            Modifier
                .alpha(viewModel.aviator11XBarrier4Yalpha)
                .offset {
                    IntOffset(
                        Aviator11OffsetXList[viewModel.aviator11XBarrier4X],
                        aviator11Barrier4Y
                    )
                })
    }
}

@Composable
private fun aviator11YAnimation(aviator11animation: Boolean, aviator11FinishedListener: ((Int) -> Unit))
        = animateIntAsState(targetValue = if (aviator11animation) -2000 else 1000,
    animationSpec = tween(4000, 0, LinearEasing),
    finishedListener = aviator11FinishedListener)

@Composable
fun Aviator11PolygonPauseBtn(aviator11PauseStatus: Boolean, aviator11PauseAction: () -> Unit) {
    Box(Modifier
        .fillMaxSize()
        .padding(10.dp), contentAlignment = Alignment.TopEnd) {
        Image(painter = painterResource(id = if (!aviator11PauseStatus) R.drawable.pause else R.drawable.play),
            contentDescription = "",
            Modifier.size(30.dp).clickable(onClick = aviator11PauseAction))
    }
}

@Composable
fun Aviator11PolygonScoreLifeCoinsTab(aviator11Lives: Int, aviator11Score: Int, aviator11Coins: Int) {
    Box(Modifier
        .fillMaxSize()
        .padding(top = 10.dp), contentAlignment = Alignment.TopCenter) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 50.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(contentAlignment = Alignment.TopStart) {
                Image(painter = painterResource(id = R.drawable.lives), contentDescription = "")
                Text(text = aviator11Lives.toString(),
                    Modifier.padding(start = 5.dp),
                    style = Typography.subtitle1)
            }
            Box(contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.score), contentDescription = "")
                Text(text = aviator11Score.toString(), style = Typography.body1)
            }
            Box(contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.coins), contentDescription = "")
                Text(text = aviator11Coins.toString(), Modifier.padding(start = 20.dp), style = Typography.body2)
            }
        }
    }
}

@Composable
fun Aviator11PolygonBetBtn(
    aviator11Polygon: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.bet01), contentDescription = "",
                Modifier.width(100.dp).height(40.dp).clickable(onClick = aviator11Polygon))
        }
    }
}

@Composable
fun Aviator11PolygonControlButtons(aviator11ArrowLeft: () -> Unit, aviator11ArrowRight: () -> Unit, aviator11BtnsAlpha: Float) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .alpha(aviator11BtnsAlpha),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.arrow01), contentDescription = "")
            Image(painter = painterResource(id = R.drawable.arrow02), contentDescription = "")
        }
    }
    Box(Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(Modifier.fillMaxWidth()) {
            Spacer(
                Modifier
                    .weight(1f)
                    .height(300.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = aviator11ArrowLeft
                    ))
            Spacer(
                Modifier
                    .weight(1f)
                    .height(300.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = aviator11ArrowRight
                    ))
        }
    }
}
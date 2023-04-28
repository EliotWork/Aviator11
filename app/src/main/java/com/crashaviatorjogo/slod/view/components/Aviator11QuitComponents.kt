package com.crashaviatorjogo.slod.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun aviator11QuitConstraint(): ConstraintSet {
    return ConstraintSet {
        val av11qBg = createRefFor("av11qBg")
        val av11qTitle = createRefFor("av11qTitle")
        val av11qOk = createRefFor("av11qOk")
        val av11qClose = createRefFor("av11qClose")
        val av11qContent = createRefFor("av11qContent")

        constrain(av11qBg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(av11qTitle) {
            top.linkTo(av11qBg.top)
            bottom.linkTo(av11qBg.top)
            start.linkTo(av11qBg.start)
            end.linkTo(av11qBg.end)
        }
        constrain(av11qOk) {
            start.linkTo(av11qBg.start)
            end.linkTo(av11qBg.end, margin = 70.dp)
            bottom.linkTo(av11qBg.bottom)
            top.linkTo(av11qBg.bottom)
        }
        constrain(av11qClose) {
            start.linkTo(av11qBg.start, margin = 70.dp)
            end.linkTo(av11qBg.end)
            bottom.linkTo(av11qBg.bottom)
            top.linkTo(av11qBg.bottom)
        }
        constrain(av11qContent) {
            start.linkTo(av11qBg.start)
            end.linkTo(av11qBg.end)
            bottom.linkTo(av11qOk.top)
            top.linkTo(av11qTitle.bottom)
        }
    }
}
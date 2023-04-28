package com.crashaviatorjogo.slod.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun aviator11DoneConstraint(): ConstraintSet {
    return ConstraintSet {
        val av11dBg = createRefFor("av11dBg")
        val av11dTitle = createRefFor("av11dTitle")
        val av11dRestart = createRefFor("av11dRestart")
        val av11dMenu = createRefFor("av11dMenu")
        val av11dContent = createRefFor("av11dContent")

        constrain(av11dBg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(av11dTitle) {
            top.linkTo(av11dBg.top)
            bottom.linkTo(av11dBg.top)
            start.linkTo(av11dBg.start)
            end.linkTo(av11dBg.end)
        }
        constrain(av11dRestart) {
            start.linkTo(av11dBg.start, margin = 30.dp)
            bottom.linkTo(av11dBg.bottom)
            top.linkTo(av11dBg.bottom)
        }
        constrain(av11dMenu) {
            end.linkTo(av11dBg.end, margin = 30.dp)
            bottom.linkTo(av11dBg.bottom)
            top.linkTo(av11dBg.bottom)
        }
        constrain(av11dContent) {
            start.linkTo(av11dBg.start)
            end.linkTo(av11dBg.end)
            bottom.linkTo(av11dRestart.top)
            top.linkTo(av11dTitle.bottom)
        }
    }
}
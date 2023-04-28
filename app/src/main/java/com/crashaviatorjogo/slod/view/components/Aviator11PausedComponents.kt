package com.crashaviatorjogo.slod.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun aviator11PausedConstraint(): ConstraintSet {
    return ConstraintSet {
        val av11pBg = createRefFor("av11pBg")
        val av11pTitle = createRefFor("av11pTitle")
        val av11pRestart = createRefFor("av11pRestart")
        val av11pMenu = createRefFor("av11pMenu")
        val av11pResume = createRefFor("av11pResume")
        val av11pContent = createRefFor("av11pContent")

        constrain(av11pBg) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(av11pTitle) {
            top.linkTo(av11pBg.top)
            bottom.linkTo(av11pBg.top)
            start.linkTo(av11pBg.start)
            end.linkTo(av11pBg.end)
        }
        constrain(av11pRestart) {
            start.linkTo(av11pBg.start)
            end.linkTo(av11pResume.start, margin = 10.dp)
            bottom.linkTo(av11pBg.bottom)
            top.linkTo(av11pBg.bottom)
        }
        constrain(av11pMenu) {
            start.linkTo(av11pResume.end, margin = 10.dp)
            end.linkTo(av11pBg.end)
            bottom.linkTo(av11pBg.bottom)
            top.linkTo(av11pBg.bottom)
        }
        constrain(av11pResume) {
            start.linkTo(av11pBg.start,)
            end.linkTo(av11pBg.end)
            bottom.linkTo(av11pBg.bottom)
            top.linkTo(av11pBg.bottom)
        }
        constrain(av11pContent) {
            start.linkTo(av11pBg.start)
            end.linkTo(av11pBg.end)
            bottom.linkTo(av11pRestart.top)
            top.linkTo(av11pTitle.bottom)
        }
    }
}
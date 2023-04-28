package com.crashaviatorjogo.slod.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.view.components.*
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11PolygonViewModel

@Composable
fun Aviator11PolygonScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<Aviator11PolygonViewModel>()
    Aviator11AppBg(viewModel.aviator11Background)
    Aviator11AppMenu { navHostController.popBackStack("Aviator11MenuScreen", false) }
    Aviator11PolygonPauseBtn(viewModel.aviator11Paused) { viewModel.aviator11PauseFlight() }
    Aviator11PolygonScoreLifeCoinsTab(viewModel.aviator11Lives, viewModel.aviator11Score, viewModel.aviator11PolygonCoins)
    Aviator11PolygonControlButtons(
        aviator11ArrowLeft = { viewModel.aviator11ChangePosition(false) },
        aviator11ArrowRight = { viewModel.aviator11ChangePosition(true) },
        aviator11BtnsAlpha = viewModel.aviator11ControlBtnsAlpha
    )
    Aviator11PolygonItems(viewModel)

    AnimatedVisibility(viewModel.aviator11DoneScreenVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Aviator11DoneScreen(navHostController,viewModel)
    }

    AnimatedVisibility(viewModel.aviator11PauseScreenVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Aviator11PausedScreen(navHostController,viewModel)
    }

    LaunchedEffect(true) {
        viewModel.aviator11StartFlight()
    }
}
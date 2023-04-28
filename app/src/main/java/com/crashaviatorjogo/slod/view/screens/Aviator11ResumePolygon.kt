package com.crashaviatorjogo.slod.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController

@Composable
fun Aviator11ResumePolygon(navHostController: NavHostController) {
    LaunchedEffect(true) {
        navHostController.navigate("Aviator11PolygonScreen")
    }
}
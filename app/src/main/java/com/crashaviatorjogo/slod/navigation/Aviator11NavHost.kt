package com.crashaviatorjogo.slod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crashaviatorjogo.slod.view.screens.*

@Composable
fun Aviator11NavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "Aviator11MenuScreen") {
        composable("Aviator11MenuScreen") { Aviator11MenuScreen(navHostController) }
        composable("Aviator11BetScreen") { Aviator11BetScreen(navHostController) }
        composable("Aviator11PolygonScreen") { Aviator11PolygonScreen(navHostController) }
        composable("Aviator11ShopScreen") { Aviator11ShopScreen(navHostController) }
        composable("Aviator11SettingsScreen") { Aviator11SettingsScreen(navHostController) }
        composable("Aviator11ResumePolygon") { Aviator11ResumePolygon(navHostController) }
    }
}
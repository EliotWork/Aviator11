package com.crashaviatorjogo.slod.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.view.components.Aviator11AppBg
import com.crashaviatorjogo.slod.view.components.Aviator11AppMenu
import com.crashaviatorjogo.slod.view.components.Aviator11BetComponents
import com.crashaviatorjogo.slod.view.components.Aviator11Coins
import com.crashaviatorjogo.slod.view.components.aviator11SettingsConstraint
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11BetViewModel

@Composable
fun Aviator11BetScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<Aviator11BetViewModel>()
    val aviator11W = LocalConfiguration.current.screenWidthDp
    Aviator11AppBg(viewModel.aviator11Background)
    Aviator11AppMenu{ navHostController.popBackStack() }
    Aviator11Coins(viewModel.aviator11Coins)

    ConstraintLayout(aviator11SettingsConstraint(), Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bet02), contentDescription = "",
            Modifier.layoutId("av11sBg").width(340.dp).height(280.dp)
        )
        Image(painter = painterResource(id = R.drawable.close), contentDescription = "",
            Modifier
                .layoutId("av11sClose").size(35.dp)
                .clickable { navHostController.popBackStack() })
        Image(painter = painterResource(id = R.drawable.ok), contentDescription = "",
            Modifier
                .layoutId("av11sApply").size(35.dp)
                .clickable { navHostController.popBackStack() })
        Column(
            Modifier.layoutId("av11sContent"),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Aviator11BetComponents(viewModel)
            Row() {
                Box(contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(id = R.drawable.x), contentDescription = "",
                        Modifier.width(100.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = viewModel.aviator11Bet.toString(), style = Typography.button)  //TODO add
                        Image(painter = painterResource(id = R.drawable.coin), contentDescription = "",
                            Modifier.size(20.dp))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(70.dp)) {
                        Image(painter = painterResource(id = R.drawable.arrow01), contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable { viewModel.aviator11ChangeBet(false) })
                        Image(painter = painterResource(id = R.drawable.arrow02), contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable { viewModel.aviator11ChangeBet(true) })
                    }
                }
                Box(contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(id = R.drawable.x), contentDescription = "",
                        Modifier.width(100.dp))
                    Text(text = "X${viewModel.aviator11BetX}", style = Typography.button)  //TODO add
                    Row(horizontalArrangement = Arrangement.spacedBy(70.dp)) {
                        Image(painter = painterResource(id = R.drawable.arrow01), contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable { viewModel.aviator11ChangeBetX(false) })
                        Image(painter = painterResource(id = R.drawable.arrow02), contentDescription = "",
                            Modifier
                                .size(30.dp)
                                .clickable { viewModel.aviator11ChangeBetX(true) })
                    }
                }
            }
        }
    }
}
package com.crashaviatorjogo.slod.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.crashaviatorjogo.slod.R

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.view.components.*
import com.crashaviatorjogo.slod.view.ui.theme.Typography
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11ShopViewModel

@Composable
fun Aviator11ShopScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<Aviator11ShopViewModel>()
    val aviator11W = LocalConfiguration.current.screenWidthDp
    Aviator11AppBg(viewModel.aviator11Background)
    Aviator11AppMenu{ navHostController.popBackStack() }
    Aviator11Coins(viewModel.aviator11Coins)

    ConstraintLayout(aviator11ShopConstraint(), Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.text_box02), contentDescription = "",
            Modifier.layoutId("av11sBg"))
        Image(painter = painterResource(id = R.drawable.close), contentDescription = "",
            Modifier.layoutId("av11sClose")
                .clickable { navHostController.popBackStack() })
        Image(painter = painterResource(id = R.drawable.ok), contentDescription = "",
            Modifier.layoutId("av11sApply")
                .clickable { navHostController.popBackStack() })
        Image(painter = painterResource(id = R.drawable.text_shop), contentDescription = "",
            Modifier.layoutId("av11sTitle").width(Dp(aviator11W / 2f)))
        Row(Modifier.layoutId("av11sContent"),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = stringResource(id = R.string.background).uppercase(), style = Typography.h4)
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow01), contentDescription = "",
                        Modifier.size(20.dp)
                            .clickable { viewModel.aviator11ChangeBgItem(false) })
                    Image(painter = painterResource(id = viewModel.aviator11BgList[viewModel.aviator11BgItem].aviator11BgSkin),
                        contentDescription = "",
                        Modifier.height(80.dp))
                    Image(painter = painterResource(id = R.drawable.arrow02), contentDescription = "",
                        Modifier.size(20.dp)
                            .clickable { viewModel.aviator11ChangeBgItem(true) })
                }
                Aviator11ShopPrice(Modifier.clickable { viewModel.aviator11PurchaseBackground() }
                    .alpha(
                        if (viewModel.aviator11BgList[viewModel.aviator11BgItem].aviator11BgAvailable) 0f
                        else 1f))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = stringResource(id = R.string.plane).uppercase(), style = Typography.h4)
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow01), contentDescription = "",
                        Modifier.size(20.dp)
                            .clickable { viewModel.aviator11ChangePlaneItem(false) })
                    Image(painter = painterResource(id = viewModel.aviator11PlaneList[viewModel.aviator11PlaneItem].aviator11PlaneSkin),
                        contentDescription = "",
                        Modifier.height(80.dp).width(70.dp))
                    Image(painter = painterResource(id = R.drawable.arrow02), contentDescription = "",
                        Modifier.size(20.dp)
                            .clickable { viewModel.aviator11ChangePlaneItem(true) })
                }
                Aviator11ShopPrice(Modifier.clickable { viewModel.aviator11PurchasePlane() }
                    .alpha(
                        if (viewModel.aviator11PlaneList[viewModel.aviator11PlaneItem].aviator11PlaneAvailable) 0f
                        else 1f))
            }
        }
    }
}
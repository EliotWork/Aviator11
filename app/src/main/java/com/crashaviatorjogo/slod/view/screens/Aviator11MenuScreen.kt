package com.crashaviatorjogo.slod.view.screens

import android.app.Activity
import android.content.Context
import com.crashaviatorjogo.slod.R

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.crashaviatorjogo.slod.view.components.Aviator11MenuBg
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11MenuViewModel

@Composable
fun Aviator11MenuScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<Aviator11MenuViewModel>()
    Aviator11MenuBg(viewModel.aviator11Background)
    val context = LocalContext.current

    Box(Modifier.fillMaxSize().padding(end = 60.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Image(painter = painterResource(id = R.drawable.start), contentDescription = "",
                Modifier.clickable { navHostController.navigate("Aviator11PolygonScreen") })
            Image(painter = painterResource(id = R.drawable.shop), contentDescription = "",
                Modifier.clickable { navHostController.navigate("Aviator11ShopScreen") })
            Image(painter = painterResource(id = R.drawable.settings01), contentDescription = "",
                Modifier.clickable { navHostController.navigate("Aviator11SettingsScreen") })
            Image(painter = painterResource(id = R.drawable.quit), contentDescription = "",
                Modifier.clickable { viewModel.aviator11QuitScreenVisibility = true })
        }
    }

    LaunchedEffect(true) {
        viewModel.aviator11GetBackground()
    }

    AnimatedVisibility(
        visible = viewModel.aviator11QuitScreenVisibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Aviator11QuitScreen(viewModel)
    }
    Box(Modifier.fillMaxSize().padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(text = stringResource(id = R.string.privacy_policy),
            Modifier.clickable { openTab(context) })
    }
}

fun openTab(context: Context) {
    val packageName = "com.android.chrome"
    val URL = "https://docs.google.com/document/d/1Xjl7-TEDyvb0K0-qfYu6y6hA8AS47RjlxdTOHyc0-Mo "

    val activity = (context as? Activity)
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    builder.setInstantAppsEnabled(true)

    builder.setToolbarColor(ContextCompat.getColor(context, R.color.white))
    val customBuilder = builder.build()

    if (packageName != null) {
        customBuilder.intent.setPackage(packageName)
        customBuilder.launchUrl(context, Uri.parse(URL))
    } else {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(URL))

        activity?.startActivity(i)
    }
}
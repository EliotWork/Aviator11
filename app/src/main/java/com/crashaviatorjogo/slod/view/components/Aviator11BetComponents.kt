package com.crashaviatorjogo.slod.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.data.Aviator11SymbolsList.aviator11Symbols
import com.crashaviatorjogo.slod.view.viewmodels.Aviator11BetViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Aviator11BetComponents(viewModel: Aviator11BetViewModel) {
    val aviator11Coroutine = rememberCoroutineScope()
    val aviator11ListState1 = rememberLazyListState()
    val aviator11ListState2 = rememberLazyListState()
    val aviator11ListState3 = rememberLazyListState()

    Box(contentAlignment = Alignment.CenterEnd) {
        Image(painter = painterResource(id = R.drawable.slot01), contentDescription = "",
            Modifier
                .width(270.dp)
                .height(110.dp)
                .padding(10.dp))
        Box(
            Modifier
                .width(270.dp)
                .height(110.dp)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
                Aviator11MachineColumn(aviator11ListState = aviator11ListState1,
                    aviator11Array = viewModel.aviator11Array)
                Aviator11MachineColumn(aviator11ListState = aviator11ListState2,
                    aviator11Array = viewModel.aviator11Array)
                Aviator11MachineColumn(aviator11ListState = aviator11ListState3,
                    aviator11Array = viewModel.aviator11Array)
            }
        }
        Image(painter = painterResource(id = R.drawable.slot02), contentDescription = "",
            Modifier
                .alpha(if (viewModel.aviator11MachineSpinning) 0f else 1f)
                .height(120.dp)
                .padding(bottom = 75.dp, end = 20.dp)
                .clickable { if (!viewModel.aviator11MachineSpinning)
                    if (viewModel.aviator11Spin()) {
                        aviator11Coroutine.launch {
                            if (viewModel.aviator11MachinePos1 == 0) aviator11ListState1.animateScrollToItem(0)
                            else {
                                aviator11ListState1.animateScrollToItem(viewModel.aviator11MachinePos1)
                            }
                        }
                        aviator11Coroutine.launch {
                            if (viewModel.aviator11MachinePos2 == 0) aviator11ListState2.animateScrollToItem(0)
                            else {
                                aviator11ListState2.animateScrollToItem(viewModel.aviator11MachinePos2)
                            }
                        }
                        aviator11Coroutine.launch {
                            if (viewModel.aviator11MachinePos3 == 0) aviator11ListState3.animateScrollToItem(0)
                            else {
                                aviator11ListState3.animateScrollToItem(viewModel.aviator11MachinePos3)
                            }
                            delay(500)
                            viewModel.aviator11CalculateCoins()
                            viewModel.aviator11MachineSpinning = false
                            viewModel.aviator11AddMachineIndexes()
                        }
                    }
                })
        Image(painter = painterResource(id = R.drawable.slot03), contentDescription = "",
            Modifier
                .alpha(if (viewModel.aviator11MachineSpinning) 1f else 0f)
                .height(120.dp)
                .padding(top = 75.dp, end = 20.dp))
    }
}

@Composable
fun Aviator11MachineColumn(aviator11ListState: LazyListState, aviator11Array: Array<Int>) {
    LazyColumn(
        Modifier.size(35.dp),
        state = aviator11ListState,
        userScrollEnabled = false,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(aviator11Array) {
            Aviator11MachineItem(it)
        }
    }
}

@Composable
fun Aviator11MachineItem(aviator11MachineSymbol: Int) {
    Image(painter = painterResource(id = aviator11Symbols[aviator11MachineSymbol]), contentDescription = "",
        Modifier.size(35.dp))
}
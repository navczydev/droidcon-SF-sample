package com.navgde.droidconsf.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R

/**
 * @author Nav Singh
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    isBackEnable: Boolean,
    onClickAction: () -> Unit,
    onBackPressed: () -> Unit,
    startAnotherActivity: () -> Unit,
    modifier: Modifier = Modifier
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    MaterialTheme {
        BackHandler(enabled = isBackEnable) {
            onBackPressed()
        }
        Column(
            modifier = modifier, verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.predictive_back_gesture_demo))
            OutlinedButton(onClick = startAnotherActivity) {
                Text(stringResource(R.string.launch_second_activity))
            }
            OutlinedButton(onClick = onClickAction) {
                Text(stringResource(R.string.flip_the_back_behaviour))
            }
            OutlinedButton(onClick = { openBottomSheet = !openBottomSheet }) {
                Text(stringResource(R.string.open_bottom_sheet))
            }
        }
        if(openBottomSheet) {
            BottomSheetSample(bottomSheetScaffoldState = scaffoldState)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevContent() {
    Content(
        isBackEnable = true,
        onClickAction = { },
        onBackPressed = { },
        startAnotherActivity = { })
}
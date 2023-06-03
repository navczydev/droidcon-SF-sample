package com.navgde.droidconsf.composables

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R

/**
 * @author Nav Singh
 */
@Composable
fun Content(
    isBackEnable: Boolean,
    onClickAction: () -> Unit,
    onBackPressed: () -> Unit,
    startAnotherActivity: () -> Unit,
    openSideSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        BackHandler(enabled = isBackEnable) {
            onBackPressed()
        }
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding16dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.whats_up_with_back_demo),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding16dp)))
            OutlinedButton(
                onClick = startAnotherActivity,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.launch_second_activity))
            }
            OutlinedButton(
                onClick = onClickAction,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.flip_the_back_behaviour))
            }
            OutlinedButton(
                onClick = openSideSheet,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.side_sheet_label))
            }
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
        startAnotherActivity = { }, {})
}
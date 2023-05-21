package com.navgde.droidconsf.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R

/**
 * @author Nav Singh
 */
@Composable
fun AlertUnsavedInfo(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(onDismissRequest = onDismiss, confirmButton = {
        Button(onClick = onConfirm) {
            Text(text = stringResource(R.string.ok))
        }

    }, title = {
        Text(text = stringResource(R.string.are_you_sure))
    }, text = {
        Text(text = stringResource(R.string.you_have_some_un_saved_information))
    })
}

@Preview
@Composable
private fun PrevAlertUnSavedInfo() {
    AlertUnsavedInfo(onConfirm = {}) {

    }
}
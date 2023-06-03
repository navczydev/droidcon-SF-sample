package com.navgde.droidconsf.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R

/**
 * @author Nav Singh
 */
@Composable
fun SideSheetContent(onClose: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding16dp))) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(R.string.content_description_close_sidesheet)
                )
            }
        }
        RowSwitchable(stringResource(R.string.events))
        RowSwitchable(stringResource(R.string.updates))
        RowSwitchable(stringResource(R.string.alerts))
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevSideSheetContent() {
    SideSheetContent {}
}

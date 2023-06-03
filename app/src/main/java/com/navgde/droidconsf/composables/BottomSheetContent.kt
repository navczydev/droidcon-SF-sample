package com.navgde.droidconsf.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R

/**
 * @author Nav Singh
 */
@Composable
fun BottomSheetContent() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding16dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.droidcon_logo_blau_rgb__origin_1),
                contentDescription = stringResource(R.string.content_description_droidcon_logo)
            )
            Image(
                painter = painterResource(id = R.drawable.droidcon_icon),
                contentDescription = stringResource(R.string.content_description_android_droidcon_logo)
            )
            Text(text = stringResource(id = R.string.future_droidcons), style = MaterialTheme.typography.headlineMedium)
            Text(text = stringResource(id = R.string.dc_berlin), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(id = R.string.dc_nyc), style = MaterialTheme.typography.bodyMedium)
            Text(text = stringResource(id = R.string.dc_london), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContent() {
    BottomSheetContent()
}
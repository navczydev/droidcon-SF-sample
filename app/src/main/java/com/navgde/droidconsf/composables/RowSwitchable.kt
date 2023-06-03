package com.navgde.droidconsf.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author Nav Singh
 */
@Composable
fun RowSwitchable(label: String) {

    var isChecked by remember {
        mutableStateOf(true)
    }


    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                isChecked = checked
            }
        )

        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = label
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRowSwitchable() {
    MaterialTheme {
        RowSwitchable("Label goes here")
    }
}

package com.navgde.droidconsf.composables

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.activities.SecondActivity

/**
 * @author Nav Singh
 */
@Composable
fun TopComposable(showSideSheet: () -> Unit = {}) {
    val context = LocalContext.current
    var isBackEnable by remember {
        mutableStateOf(false)
    }
    var shouldShowAlert by remember {
        mutableStateOf(false)
    }
    Content(isBackEnable = isBackEnable, onClickAction = {
        isBackEnable = !isBackEnable
    }, onBackPressed = {
        shouldShowAlert = !shouldShowAlert
    }, startAnotherActivity = {
        context.startActivity(Intent(context, SecondActivity::class.java))
    }, openSideSheet = {
        showSideSheet()
    })

    AnimatedVisibility(shouldShowAlert) {
        AlertUnsavedInfo(
            onConfirm = { shouldShowAlert = !shouldShowAlert },
            onDismiss = { shouldShowAlert = !shouldShowAlert }
        )
    }

}

@Preview
@Composable
private fun PrevTopComposable() {
    TopComposable()
}

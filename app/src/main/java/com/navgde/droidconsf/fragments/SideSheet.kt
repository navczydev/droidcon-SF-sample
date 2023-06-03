package com.navgde.droidconsf.fragments

import android.content.Context
import android.view.View
import androidx.activity.addCallback
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.android.material.sidesheet.SideSheetCallback
import com.google.android.material.sidesheet.SideSheetDialog
import com.navgde.droidconsf.composables.SideSheetContent
import com.navgde.droidconsf.databinding.SideSheetContentBinding

/**
 * @author Nav Singh
 */
object SideSheet {

    fun showSideSheet(context: Context, sideSheetContentBinding: SideSheetContentBinding) {

        val sideSheetDialog = SideSheetDialog(context)
        sideSheetDialog.behavior.addCallback(object : SideSheetCallback() {
            override fun onStateChanged(sheet: View, newState: Int) {
                if (newState == SideSheetBehavior.STATE_DRAGGING) {
                    sideSheetDialog.behavior.state = SideSheetBehavior.STATE_EXPANDED
                }
            }

            override fun onSlide(sheet: View, slideOffset: Float) {
            }
        })
        sideSheetDialog.window?.decorView?.run {
            // required to use composables in Dialog
            setViewTreeLifecycleOwner(sideSheetDialog)
            setViewTreeSavedStateRegistryOwner(sideSheetDialog)
            setViewTreeOnBackPressedDispatcherOwner(sideSheetDialog)
        }
        sideSheetDialog.setContentView(sideSheetContentBinding.root)
        // set teh composable content
        sideSheetContentBinding.cvHolder.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    SideSheetContent {
                        sideSheetDialog.dismiss()
                    }
                }
            }
        }

        // TODO -: add callback to SideSheet
        /*sideSheetDialog.onBackPressedDispatcher.addCallback {

        }*/

        sideSheetDialog.show()
    }
}
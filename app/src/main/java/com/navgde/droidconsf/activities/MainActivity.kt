package com.navgde.droidconsf.activities

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.window.BackEvent
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.BuildCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.navgde.droidconsf.R
import com.navgde.droidconsf.composables.TopComposable
import com.navgde.droidconsf.databinding.ActivityMainBinding
import com.navgde.droidconsf.viewmodels.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author Nav Singh
 */
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    @BuildCompat.PrereleaseSdkCheck
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.cv.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    TopComposable()
                }
            }
        }
            /*     overrideActivityTransition(
                     OVERRIDE_TRANSITION_CLOSE,
                     0,0
                 )*/
            // starting from AppCompat 1.6.0-alpha05 (backward compatible)
            // OnBackInvokedCallback platform AP
            //  addLifecycleAwareBackPressCallback()
            //    addLifecycleUnAwareBackPressCallback()
            dialogBackPressCallback()
            // TODO- PLATFORM API
            /*if (BuildCompat.isAtLeastT()) {
                val callback =
                    OnBackInvokedCallback {
                        Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch {
                            mainActivityViewModel.updateFlag(shouldUnregister = true)
                        }
                    }
                // Step 2.
                onBackInvokedDispatcher.registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT,
                    callback
                )
                // Step 3.
                lifecycleScope.launch {
                    mainActivityViewModel.backCallback.collectLatest { backFlag ->
                        if (backFlag) {
                            onBackInvokedDispatcher.unregisterOnBackInvokedCallback(callback)
                        }
                    }
                }
            }*/

            val box = findViewById<View>(R.id.cv)
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val maxXShift = (screenWidth / 20)
            val intialX = box.x
            val intialY = box.y

            // For the purposes of demonstration, this code snippet presents the
            // callback as being always enabled. However, in practice it's always a
            // good idea to always disable the callback when you're ready for the
            // system to handle back events.
            val callback = object : OnBackPressedCallback(enabled = true) {

                override fun handleOnBackProgressed(backEvent: BackEvent) {
                    Log.d(TAG, "handleOnBackProgressed: Back gesture progressing")
                    when (backEvent.swipeEdge) {
                        BackEvent.EDGE_LEFT ->
                            box.translationX = backEvent.progress * maxXShift

                        BackEvent.EDGE_RIGHT ->
                            box.translationX = -(backEvent.progress * maxXShift)
                    }
                    box.scaleX = 1F - (0.1F * backEvent.progress)
                    box.scaleY = 1F - (0.1F * backEvent.progress)
                }

                override fun handleOnBackPressed() {
                    // Do something after the back gesture completes.
                    Log.d(TAG, "handleOnBackPressed: Back gesture completed")
                }

                override fun handleOnBackCancelled() {
                    // e.g. reset box to the original position
                    Log.d(TAG, "handleOnBackCancelled: Back gesture cancelled")
                    box.x = intialX
                    box.y = intialY
                    this.remove()

                }
            }
            this.onBackPressedDispatcher.addCallback(callback)


    }


    // Always called
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // need to call this to process the callbacks added to the onBackDispatcher
        super.onBackPressed()
        Log.d(TAG, "onBackPressed: MAIN_ACTIVITY")
    }

    /**
     * lifecycle aware callback - No need to manually remove this callback
     */
    private fun addLifecycleAwareBackPressCallback() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, "handleOnBackPressed: lifecycle aware callback")
                //isEnabled = false
            }

        })

        onBackPressedDispatcher.hasEnabledCallbacks()
    }

    /**
     * lifecycle unaware callback - Manually remove this callback
     */
    private fun addLifecycleUnAwareBackPressCallback() {
        // Define a callback
        val callback = onBackPressedDispatcher.addCallback(enabled = true) {
            // Do your custom stuff here..
        }

        // remove the callback from onBackPressedDispatcher
        callback.remove()

        // update the enable state
        callback.isEnabled = true

    }

    /**
     * handle backPress at the dialog level
     */
    private fun dialogBackPressCallback() {
        val dialog = MaterialAlertDialogBuilder(this).setTitle("Hello")
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog, which -> dialog.dismiss() }.create().apply {
                this.onBackPressedDispatcher.addCallback(this) {
                    Toast.makeText(context, "Dialog handling backPress", Toast.LENGTH_SHORT).show()
                    remove()
                }
                //setCancelable(true)
            }
        dialog.show()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}



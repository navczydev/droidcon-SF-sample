package com.navgde.droidconsf.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.BuildCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.navgde.droidconsf.R
import com.navgde.droidconsf.composables.TopComposable
import com.navgde.droidconsf.databinding.ActivityMainBinding
import com.navgde.droidconsf.viewmodels.MainActivityViewModel

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
        // starting from AppCompat 1.6.0-alpha05 (backward compatible)
        //  addLifecycleAwareBackPressCallback()
        //  addLifecycleUnAwareBackPressCallback()
        //  dialogBackPressCallback()

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
    }


    // Always called
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        // need to call this to process the callbacks added to the onBackDispatcher
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



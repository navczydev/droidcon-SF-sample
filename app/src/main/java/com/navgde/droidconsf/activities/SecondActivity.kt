package com.navgde.droidconsf.activities

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.window.BackEvent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.view.isVisible
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSeekController
import com.navgde.droidconsf.R
import com.navgde.droidconsf.composables.SanFranciscoImage
import com.navgde.droidconsf.databinding.ActivitySecondBinding


/**
 * @author Nav Singh
 */
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.cvHolder.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SanFranciscoImage()
            }
        }
        // Custom cross-activity animations
        overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE, R.anim.slide_in, R.anim.slide_out)
        // overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_in, R.anim.slide_out)

        val composeHolder = binding.cvHolder
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val maxXShift = (screenWidth / 20)
        // Progress callbacks available since :-
        // https://developer.android.com/jetpack/androidx/releases/activity#1.8.0-alpha01
        val callbackCustomProgressDemo = object : OnBackPressedCallback(enabled = true) {

            override fun handleOnBackProgressed(backEvent: BackEvent) {
                Log.d(TAG, "handleOnBackProgressed: Back gesture progressing")
                when (backEvent.swipeEdge) {
                    BackEvent.EDGE_LEFT ->
                        composeHolder.translationX = backEvent.progress * maxXShift

                    BackEvent.EDGE_RIGHT ->
                        composeHolder.translationX = -(backEvent.progress * maxXShift)
                }
                composeHolder.scaleX = 1F - (0.1F * backEvent.progress)
                composeHolder.scaleY = 1F - (0.1F * backEvent.progress)
            }

            override fun handleOnBackPressed() {
                // Do something after the back gesture completes.
                Log.d(TAG, "handleOnBackPressed: Back gesture completed")
            }

            override fun handleOnBackCancelled() {
                // e.g. reset box to the original position
                Log.d(TAG, "handleOnBackCancelled: Back gesture cancelled")
                this.remove()

            }

            override fun handleOnBackStarted(backEvent: BackEvent) {
                super.handleOnBackStarted(backEvent)
                Log.d(TAG, "handleOnBackStarted: ")
            }
        }

        // add callback to BackPressedDispatcher
        onBackPressedDispatcher.addCallback(callbackCustomProgressDemo)

        val callbackWithTransitionsAPI = object : OnBackPressedCallback(enabled = true) {

            var controller: TransitionSeekController? = null

            override fun handleOnBackStarted(backEvent: BackEvent) {

                // https://developer.android.com/jetpack/androidx/releases/transition#1.5.0-alpha01
                controller = TransitionManager.controlDelayedTransition(
                    binding.root, // rootView that contains "textView1" and "textView2"
                    Fade() // Slide, ChangeBound, Explode, etc (Only Transitions work that supports seeking)
                )
                binding.tv1.isVisible = true
                binding.tv2.isVisible = false

            }

            override fun handleOnBackProgressed(backEvent: BackEvent) {

                if (controller?.isReady == true) {
                    controller?.currentPlayTimeMillis = (backEvent.progress * controller?.durationMillis!!).toLong()
                }

            }


            override fun handleOnBackPressed() {

                controller?.animateToEnd()
                this.isEnabled = false

            }

            override fun handleOnBackCancelled() {

                // If the user cancels the back gesture, reset the state
                TransitionManager.beginDelayedTransition(binding.root)
                binding.tv1.isVisible = true
                binding.tv2.isVisible = true

            }

        }
        this.onBackPressedDispatcher.addCallback(callbackWithTransitionsAPI)

    }


    companion object {
        private const val TAG = "SecondActivity"
    }
}
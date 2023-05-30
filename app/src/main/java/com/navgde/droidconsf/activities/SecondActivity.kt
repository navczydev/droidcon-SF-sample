package com.navgde.droidconsf.activities

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.window.BackEvent
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.navgde.droidconsf.R
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
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            Log.d(TAG, "onCreate: Android14")
            overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE, R.anim.slide_in_left, R.anim.slide_out_left)
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_in_left, R.anim.slide_out_left)


            val composeHolder = binding.cvHolder
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val maxXShift = (screenWidth / 20)


            // For the purposes of demonstration, this code snippet presents the
            // callback as being always enabled. However, in practice it's always a
            // good idea to always disable the callback when you're ready for the
            // system to handle back events.
            // Progress callbacks available since :-
            // https://developer.android.com/jetpack/androidx/releases/activity#1.8.0-alpha01
            val callback = object : OnBackPressedCallback(enabled = true) {

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
            }
            this.onBackPressedDispatcher.addCallback(callback)
        //}


    }

    companion object {
        private const val TAG = "SecondActivity"
    }
}

@Composable
fun SanFranciscoImage() {
    MaterialTheme {
        Column {
            Text("DroidConSF-23")
            Image(
                painter = painterResource(id = R.drawable.san_francisco),
                contentDescription = "Golden bridge",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
            )
        }
    }
}

@Preview
@Composable
fun PrevSanFranciscoImage() {
    SanFranciscoImage()
}
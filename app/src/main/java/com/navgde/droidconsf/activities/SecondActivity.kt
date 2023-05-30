package com.navgde.droidconsf.activities

import android.os.Bundle
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
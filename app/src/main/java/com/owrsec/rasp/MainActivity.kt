package com.owrsec.rasp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.owrsec.rasp.ui.theme.OwrSecRASPTheme
import com.owrsec.rasp.rootDetector
import com.owrsec.rasp.rootDetector2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // instanciando as classes de detecção
        var detection1:rootDetector = rootDetector()
        var detection2:rootDetector2 = rootDetector2()

        // checando se é root ou não
        if (detection1.checkRoot(this) || detection2.isDeviceRooted()) {
            finish()
            finishActivity(1)
            finishAffinity()
            finishAndRemoveTask()
            finishAfterTransition()
        }

        enableEdgeToEdge()
        setContent {
            OwrSecRASPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OwrSecRASPTheme {
        Greeting("Android")
    }
}
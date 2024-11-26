package com.github.compose_android_template


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.github.compose_android_template.ui.theme.compose_android_templateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            compose_android_templateTheme {
                Column(modifier = Modifier.safeDrawingPadding()) {
                    Text("If you used a command to replace the default project name everywhere," +
                            "\nthe lines below should change as well")
                    Text("The default package is:" +
                            "\ncom.github.compose_android_template")
                    Text("The convention plugins' names in build.gradle contain:" +
                            "\ncompose.android.template ")
                }
            }
        }
    }
}

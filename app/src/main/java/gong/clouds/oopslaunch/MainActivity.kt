package gong.clouds.oopslaunch

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import gong.clouds.oopslaunch.ui.theme.OopsLaunchTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OopsLaunchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    val packageManager: PackageManager = context.packageManager
    fun launchPackage(packageName:String){
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.let {
            launcher.launch(intent)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ){
        Text(
            text = "OOPS!",
            fontSize = 80.sp,
            modifier = modifier
        )

        Row(){
            TextButton(onClick = {
                launchPackage("org.koreader.launcher.fdroid")
            }) {
                Text("something ", fontSize = 30.sp)
            }
            TextButton(onClick = {
                launchPackage("cn.wps.moffice_eng")
            }) {
                Text("error", fontSize = 30.sp)
            }
        }
        Row(){
            TextButton(onClick = {
                launchPackage("org.gongclouds.rickplaywoodenfish")
            }) {
                Text("restart", fontSize = 30.sp)
            }
            TextButton(onClick = {
                launchPackage("org.fossify.gallery")
            }) {
                Text(" or ", fontSize = 30.sp)
            }
            TextButton(onClick = {
                launchPackage("com.kaajjo.libresudoku")
            }) {
                Text("play sudoku", fontSize = 30.sp)
            }
        }
    }
}

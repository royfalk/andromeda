package il.co.falk.andromeda

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import il.co.falk.andromeda.ui.theme.il.co.falk.andromeda.ui.theme.AndromedaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)

        setContent {
            AndromedaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainMenu(this)
                }
            }
        }
    }
}

@Composable
fun MainMenu(context: Context) {
    val msg_author = "Roy Falk"
    val msg_body = "This is a message body"

    // Load the image as an ImageBitmap
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.bottom_filler)

    // Create a ShaderBrush with repeated tiling
    val imageBrush = remember(imageBitmap) {
        ShaderBrush(
            shader = ImageShader(
                image = imageBitmap,
                tileModeX = TileMode.Clamp, // Repeat horizontally
                tileModeY = TileMode.Repeated  // Repeat vertically
            )
        )
    }

    Column(modifier = Modifier.fillMaxWidth().background(brush = imageBrush),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.spaceships_background), // Replace 'your_image_name'
            contentDescription = null, // For accessibility, can be set to null for decorative images
            modifier = Modifier
            // You can add modifiers to change size, alignment, etc.
        )

        GreenButton(context, GameActivity::class.java, "New Game")
        GreenButton(context, PlanetActivity::class.java, "Load Game")
        GreenButton(context, GameActivity::class.java, "About")
    }

}

@Composable
fun GreenButton(context: Context, activity: Class<out Activity>, text:String) {
    Surface(shape = MaterialTheme.shapes.medium,
            shadowElevation = 5.dp,
            color = Color.Black,
            border = BorderStroke(2.dp, Color(0xFF00FF00)), // bright green
        onClick = {
            val intent: Intent = Intent(context, activity)
            context.startActivity(intent)
        }) {
        Text(
            text = text,
            modifier = Modifier.padding(all = 15.dp),
            color = Color(0xFF00FF00), // bright green
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}




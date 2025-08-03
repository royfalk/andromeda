package il.co.falk.andromeda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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



    /*override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun newGame(view: android.view.View?) {
        val intent: Intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun newCombat(view: android.view.View?) {
        // This does nothing - dummy comment too
        // Still doesn't work
    }

    fun newOpengl(view: android.view.View?) {
        val intent: Intent = Intent(this, OpenGLES20Activity::class.java)
        startActivity(intent)
    }*/
}

@Composable
fun MainMenu(context: Context) {
    val msg_author = "Roy Falk"
    val msg_body = "This is a message body"
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 5.dp,
            onClick = {
                val intent: Intent = Intent(context, GameActivity::class.java)
                context.startActivity(intent)
            }) {
            Text(
                text = "New Game",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 5.dp) {
            Text(
                text = "Load Game",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 5.dp) {
            Text(
                text = "About",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
    /*Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg_author,
                 color = MaterialTheme.colorScheme.secondary,
                 style = MaterialTheme.typography.titleSmall)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(
                    text = msg_body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }*/

}


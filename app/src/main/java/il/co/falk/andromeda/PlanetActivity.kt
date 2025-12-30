package il.co.falk.andromeda

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import il.co.falk.andromeda.game.Colony
import il.co.falk.andromeda.game.Game.nextTurn
import il.co.falk.andromeda.game.Planet
import il.co.falk.andromeda.game.Ship
import il.co.falk.andromeda.ui.theme.il.co.falk.andromeda.ui.theme.AndromedaTheme

class PlanetActivity : ComponentActivity() {
    var name: String? = null
    var planet: Planet = Planet(false)
    var colony: Colony? = planet.colony


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra(PLANET_NAME)

        setContent {
            AndromedaTheme {
                PlanetScreen(
                    planetName = name,
                    colony = colony
                )
            }
        }
    }

    @Composable
    fun PlanetScreen(
        planetName: String?,
        colony: Colony?
    ) {
        var production by remember { mutableStateOf("") }
        var currentlyProducing by remember { mutableStateOf("") }
        var remaining by remember { mutableStateOf("") }
        var owner by remember { mutableStateOf("") }

        LaunchedEffect(colony) {
            if (colony != null) {
                production = planet.production.toString()
                currentlyProducing = colony.currentlyBuilding
                remaining = colony.remainingTurns.toString()
                owner = colony.player.name ?: "Uninhabited"
            } else {
                production = "-"
                currentlyProducing = "-"
                remaining = "-"
                owner = "Uninhabited"
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = planetName ?: "Unknown Planet",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(8.dp))

            Text("Production: $production")
            Text("Currently Producing: $currentlyProducing")
            Text("Remaining: $remaining")
            Text("Owner: $owner")
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_planet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        } else if (id == R.id.action_next) {
            onNextTurn(null)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun onNextTurn(view: View?) {
        Log.d("Andromeda", "Next Turn")
        nextTurn()
    }

    fun onSelectProduct(view: View?) {
        val intent = Intent(this, SelectProductActivity::class.java)
        startActivityForResult(intent, SelectProductActivity.ACTIVITY_CODE)
    }


    internal inner class UnitArrayAdapter(val ctx: Context, ships: List<Ship?>?) :
        ArrayAdapter<Ship?>(ctx, R.layout.unit_row, ships!!) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val u = getItem(position)

            val inflater = ctx
                .getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView = inflater.inflate(R.layout.unit_row, parent, false)

            // Set planet name
            val textView = rowView.findViewById<View>(R.id.unitNameTextView) as TextView
            textView.text = u!!.name

            // Set planet production
            val hpBar = rowView.findViewById<View>(R.id.hpBar) as ProgressBar
            hpBar.progress = u.hp

            // TODO: enemy ships
            return rowView
        }
    }

    companion object {
        const val PLANET_NAME: String = "PLANET_NAME"
    }
}

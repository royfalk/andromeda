package il.co.falk.andromeda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;
import il.co.falk.andromeda.game.UnitFactory;
import il.co.falk.andromeda.game.Universe;


public class PlanetActivity extends ActionBarActivity {
    public static final String PLANET_NAME = "PLANET_NAME";

    Planet planet;

    TextView planetNameView, productionView, currentlyProducingView, owner, remaining, turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        Intent intent = getIntent();
        String name = intent.getStringExtra(PlanetActivity.PLANET_NAME);

        planet = Universe.getUniverse().getPlanetByName(name);

        planetNameView = (TextView)findViewById(R.id.planetName);
        planetNameView.setText(name);

        productionView = (TextView)findViewById(R.id.production);
        productionView.setText(String.valueOf(planet.production));

        currentlyProducingView = (TextView)findViewById(R.id.currentlyProducing);
        currentlyProducingView.setText(planet.colony.currentlyBuilding.name);

        remaining = (TextView)findViewById(R.id.remaining);
        remaining.setText("");

        turns = (TextView)findViewById(R.id.turns);
        turns.setText("");

        // Set planet owner and color
        if(planet.colony != null) {
            Player player = planet.colony.player;
            owner = (TextView) findViewById(R.id.ownerTextView);
            owner.setText(player.name);
            owner.setTextColor(player.color);

            Colony c = planet.colony;
            remaining.setText(c.queue + "/" + c.currentlyBuilding.cost);
            turns.setText(c.getRemainingTurns() + " turns");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_planet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSelectProduct(View view) {
        Intent intent = new Intent(this, SelectProductActivity.class);
        startActivityForResult(intent, SelectProductActivity.ACTIVITY_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SelectProductActivity.ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                planet.colony.currentlyBuilding = UnitFactory.getUnitFactory().getUnit(data.getStringExtra(SelectProductActivity.PRODUCT), planet.location);
                currentlyProducingView.setText(planet.colony.currentlyBuilding.name);
            }
        }


    }
}

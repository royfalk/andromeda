package il.co.falk.andromeda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Universe;


public class PlanetActivity extends ActionBarActivity {
    public static final String PLANET_NAME = "PLANET_NAME";

    Planet planet;

    TextView planetNameView, productionView, currentlyProducingView;

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
        productionView.setText("Production: " + planet.production);

        currentlyProducingView = (TextView)findViewById(R.id.currentlyProducing);
        currentlyProducingView.setText("Currently Producing: ");
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
}

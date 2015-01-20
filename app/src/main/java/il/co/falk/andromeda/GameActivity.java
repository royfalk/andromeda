package il.co.falk.andromeda;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.UnitFactory;
import il.co.falk.andromeda.game.Unit;
import il.co.falk.andromeda.game.Universe;

public class GameActivity extends ActionBarActivity {
    Universe universe;
    ArrayList<PlanetView> planets;
    public static final int SELECT_PRODUCT = 965;
    Colony activeColony;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Init UnitFactory
        UnitFactory.getUnitFactory(this.getApplicationContext());

        universe = Universe.getUniverse();

        repopulatePlanets();
        updateGUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void nextTurn(View view) {
        universe.nextTurn();
        updateGUI();
    }

    public void repopulatePlanets() {
        LinearLayout ll = (LinearLayout)findViewById(R.id.planetsLayout);
        ll.removeAllViews();

        planets = new ArrayList<>();

        for(Planet p : universe.planets) {
            PlanetView pv;
            if(p.colony!=null) {
                if(p.colony.player == universe.player) {
                    pv = new PlayerColonyView(this, getApplicationContext(), p.colony, p);
                } else {
                    pv = new ColonyView(this, getApplicationContext(), p.colony, p);
                }
            } else {
                pv = new UninhabitedPlanetView(this, getApplicationContext(), p);
            }
            ll.addView(pv);
            planets.add(pv);
        }

        updateGUI();
    }

    void updateGUI() {
        boolean canColonize = universe.player.canColonize();
        boolean canAttack = universe.player.canAttack();

        for(PlanetView pv : planets) {
            pv.updateView(canColonize, canAttack);
        }

        // check if end of game
        if(universe.gameEnded()) {
            if(universe.player == universe.players.get(0))
                Log.d("Victory", "Victory");
            else
                Log.d("Defeat", "Defeat");

            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == SELECT_PRODUCT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String unitName = data.getStringExtra("product");

                Unit u = UnitFactory.getUnitFactory().getUnit(unitName, activeColony.planet.location);
                activeColony.currentlyBuilding = u;
                activeColony = null;
                updateGUI();
            }
        }
    }


}

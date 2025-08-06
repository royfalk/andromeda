package il.co.falk.andromeda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Game;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.NamesFactory;
import il.co.falk.andromeda.game.ProductFactory;

public class GameActivity extends Activity {
    ArrayList<PlanetView> planets;
    public static final int SELECT_PRODUCT = 965;
    Colony activeColony;
    StarMapView starMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Init ProductFactory
        ProductFactory.getUnitFactory(this.getApplicationContext());

        // Init PlanetFactory
        new NamesFactory(this.getApplicationContext());


        // Create Universe GUI
        StarMapView map = (StarMapView) findViewById(R.id.star_map);
        View root = map.getRootView();
        root.setBackgroundColor(Color.rgb(0,0,0));

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
        } else if (id == R.id.action_game) {
            onGame(null);
            return true;
        } else if (id == R.id.action_colonies) {
            onColonies(null);
            return true;
        } else if (id == R.id.action_planets) {
            onPlanets(null);
            return true;
        } else if (id == R.id.action_races) {
            onRaces(null);
            return true;
        } else if (id == R.id.action_tech) {
            onTech(null);
            return true;
        } else if (id == R.id.action_next) {
            onNextTurn(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextTurn(View view) {
        Game.INSTANCE.nextTurn();
        updateGUI();
    }

    public void repopulateStarMapWithPlanets(int w, int h) {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.star_map);

        for (Planet p : Game.INSTANCE.getPlanets()) {
            TextView tv = new TextView(getApplicationContext());
            tv.setText(p.name);
            rl.addView(tv);
            float x = w / 102 * (1 + p.location.x);
            float y = h / 102 * (1 + p.location.y);
            tv.setX(x);
            tv.setY(y);
        }
    }

    public void repopulatePlanets() {
        //LinearLayout ll = (LinearLayout) findViewById(R.id.planetsLayout);
        //ll.removeAllViews();

        planets = new ArrayList<>();

        for (Planet p : Game.INSTANCE.getPlanets()) {
            PlanetView pv;
            if (p.colony != null) {
                if (p.colony.player.getHuman()) {
                    pv = new PlayerColonyView(this, getApplicationContext(), p.colony, p);
                } else {
                    pv = new ColonyView(this, getApplicationContext(), p.colony, p);
                }
            } else {
                pv = new UninhabitedPlanetView(this, getApplicationContext(), p);
            }
            //ll.addView(pv);
            planets.add(pv);
        }

        updateGUI();
    }

    void updateGUI() {
        boolean canColonize = Game.INSTANCE.getHumanPlayer().canColonize();
        boolean canAttack = Game.INSTANCE.getHumanPlayer().canAttack();

        for (PlanetView pv : planets) {
            pv.updateView(this.getApplicationContext(), canColonize, canAttack);
        }

        // check if end of game
        if (Game.INSTANCE.gameEnded()) {
            if (!Game.INSTANCE.getPlayers().get(0).getDead())
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


                activeColony.currentlyBuilding = unitName;
                activeColony = null;
                updateGUI();
            }
        }
    }


    public void onGame(View view) {
    }

    public void onColonies(View view) {
        Intent intent = new Intent(this, PlanetsActivity.class);
        intent.putExtra(PlanetsActivity.LIST_TYPE, PlanetsActivity.LIST_TYPE_COLONIES);
        startActivity(intent);
    }

    public void onPlanets(View view) {
        Intent intent = new Intent(this, PlanetsActivity.class);
        intent.putExtra(PlanetsActivity.LIST_TYPE, PlanetsActivity.LIST_TYPE_PLANETS);
        startActivity(intent);
    }

    public void onRaces(View view) {
    }

    public void onTech(View view) {
        Intent intent = new Intent(this, TechActivity.class);
        startActivity(intent);
    }

    public void onNextTurn(View view) {
        Log.d("Andromeda", "Next Turn");
        Game.INSTANCE.nextTurn();
        updateGUI();
    }
}

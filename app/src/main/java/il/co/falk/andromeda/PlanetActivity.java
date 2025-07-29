package il.co.falk.andromeda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;
import il.co.falk.andromeda.game.Unit;
import il.co.falk.andromeda.game.Universe;


public class PlanetActivity extends Activity {
    public static final String PLANET_NAME = "PLANET_NAME";

    String name;
    Planet planet;
    Colony colony;

    TextView planetNameView, productionView, currentlyProducingView, owner, remaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        Intent intent = getIntent();
        name = intent.getStringExtra(PlanetActivity.PLANET_NAME);

        planet = Universe.getUniverse().getPlanetByName(name);
        //colony = planet.colony;

        // Set Name
        planetNameView = (TextView)findViewById(R.id.planetName);
        //planetNameView.setText(name);

        // Set Production
        productionView = (TextView)findViewById(R.id.production);
        //productionView.setText(String.valueOf(planet.production));

        // Set Current Production and remaining
        currentlyProducingView = (TextView)findViewById(R.id.currentlyProducing);
        remaining = (TextView)findViewById(R.id.remaining);
        owner = (TextView) findViewById(R.id.ownerTextView);
        if(colony != null) {
            // Set planet owner and color
            //Player player = planet.colony.player;

            //owner.setText(player.name);
            //owner.setTextColor(player.color);

            //String building = colony.currentlyBuilding.name;
            //int queue = colony.queue;
            //int cost = colony.currentlyBuilding.cost;
            //int turns = colony.getRemainingTurns();
            //currentlyProducingView.setText(building);
            //remaining.setText(queue+"/"+cost+" ("+turns+")");
        } else {
            //currentlyProducingView.setText("");
            //remaining.setText("");
        }

        updateGUI();



        /*final ListView listview = (ListView) findViewById(R.id.planetaryDefensesListview);
        final UnitArrayAdapter adapter = new UnitArrayAdapter(getApplicationContext(), planet.units);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Planet p = (Planet) parent.getItemAtPosition(position);
                Intent intent = new Intent(adapter.context, PlanetActivity.class);
                intent.putExtra(PlanetActivity.PLANET_NAME, p.name);

                adapter.context.startActivity(intent);


                /*view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                /*list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);*//*

                            }
                        });*/
            /*}

        });*/
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
        } else if (id == R.id.action_next) {
            onNextTurn(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNextTurn(View view) {
        Log.d("Andromeda", "Next Turn");
        Universe.getUniverse().nextTurn();
        updateGUI();
    }

    private void updateGUI() {
        colony = planet.colony; // This way, if colony died or changed, it would change here too.

        // Set Name
        //planetNameView = (TextView)findViewById(R.id.planetName);
        //planetNameView.setText(name);

        // Set Production
        //productionView = (TextView)findViewById(R.id.production);
        productionView.setText(String.valueOf(planet.production));

        // Set Current Production and remaining
        //currentlyProducingView = (TextView)findViewById(R.id.currentlyProducing);
        remaining = (TextView)findViewById(R.id.remaining);

        if(colony != null) {
            Log.d("test","t");

            // Set planet owner and color
            Player player = planet.colony.player;
            //owner = (TextView) findViewById(R.id.ownerTextView);
            owner.setText(player.name);
            owner.setTextColor(player.color);

            String building = colony.currentlyBuilding;
            int queue = colony.queue;
            int cost = colony.currentCostToBuild;
            int turns = colony.getRemainingTurns();
            currentlyProducingView.setText(building);
            remaining.setText(queue+"/"+cost+" ("+turns+")");
        } else {
            currentlyProducingView.setText("");
            remaining.setText("");
        }

        ArrayList<Unit> units = Universe.getUniverse().getShipsAtLocation(planet.location);

        if(units!=null) {
            Button button = (Button)findViewById(R.id.fleetButton);
            button.setEnabled(true);
        }
    }

    public void onSelectProduct(View view) {
        Intent intent = new Intent(this, SelectProductActivity.class);
        startActivityForResult(intent, SelectProductActivity.ACTIVITY_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SelectProductActivity.ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String productName = data.getStringExtra(SelectProductActivity.PRODUCT);
                planet.colony.changeProductToBuild(productName);

                currentlyProducingView.setText(productName);
            }
        }


    }


    class UnitArrayAdapter extends ArrayAdapter<Unit> {
        public final Context context;

        public UnitArrayAdapter(Context context, List<Unit> units) {
            super(context, R.layout.unit_row, units);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Unit u = (Unit)getItem(position);

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.unit_row, parent, false);

            // Set planet name
            TextView textView = (TextView) rowView.findViewById(R.id.unitNameTextView);
            textView.setText(u.name);

            // Set planet production
            ProgressBar hpBar = (ProgressBar) rowView.findViewById(R.id.hpBar);
            hpBar.setProgress(u.hp);

            // TODO: enemy ships

            return rowView;
        }
    }
}

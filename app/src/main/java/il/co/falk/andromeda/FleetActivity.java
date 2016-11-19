package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;
import il.co.falk.andromeda.game.Universe;

public class FleetActivity extends ActionBarActivity {
    public static final String FLEET_LOCATION = "FLEET_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);

        Intent intent = getIntent();
        String type = intent.getStringExtra(FleetActivity.FLEET_LOCATION);

        final ListView listview = (ListView) findViewById(R.id.listview);
        Universe universe = Universe.getUniverse();

       // typon

        //final FleetArrayAdapter adapter = new PlanetsArrayAdapter(this, universe.getShipsAtLocation(universe.player));
        //listview.setAdapter(adapter);

        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Planet p = (Planet) parent.getItemAtPosition(position);
                Intent intent = new Intent(adapter.context, PlanetActivity.class);
                intent.putExtra(PlanetActivity.PLANET_NAME, p.name);

                adapter.context.startActivity(intent);
            }

        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fleet, menu);
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


    public void onNextTurn(View view) {
        Log.d("Andromeda", "Next Turn");
        Universe.getUniverse().nextTurn();
        // TODO: updateGUI?
    }
}



class FleetArrayAdapter extends ArrayAdapter<Planet> {
    public final Context context;

    public FleetArrayAdapter(Context context, List<Planet> planets) {
        super(context, R.layout.planet_row, planets);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Planet p = (Planet)getItem(position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.planet_row, parent, false);

        // Set planet name
        TextView textView = (TextView) rowView.findViewById(R.id.planetNameTextView);
        textView.setText(p.name);

        // Set planet production
        textView = (TextView) rowView.findViewById(R.id.productionTextView);
        textView.setText("Production: " + p.production);

        // Set planet owner and color
        if(p.colony != null) {
            Player player = p.colony.player;
            textView = (TextView) rowView.findViewById(R.id.ownerTextView);
            textView.setText(player.name);
            textView.setTextColor(player.color);
        }
        return rowView;
    }
}



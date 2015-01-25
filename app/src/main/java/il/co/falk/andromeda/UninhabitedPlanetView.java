package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Universe;

/**
 * Created by roy on 1/16/15.
 */
public class UninhabitedPlanetView extends PlanetView {
    // GUI
    protected Button colonize;

    public UninhabitedPlanetView(final GameActivity activity, final Context context, Planet p) {
        super(context,p);

        // An actual PlanetView instance and not subclass
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.planet_layout, null);
        addView(layout);

        leftTitle = (TextView)findViewById(R.id.leftTitle);
        leftTitle.setClickable(true);


        leftTitle.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getApplicationContext(), PlanetActivity.class);
                intent.putExtra(PlanetActivity.PLANET_NAME, planet.name);
                activity.startActivity(intent);
            }
        });

        colonize = (Button)findViewById(R.id.button_colonize);
        colonize.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Universe universe = Universe.getUniverse();
                universe.player.colonize(planet);
                activity.repopulatePlanets();
            }
        });

        leftTitle.setTextColor(Color.DKGRAY);
    }

    public void updateView(Context context, boolean canColonize, boolean canAttack) {
        leftTitle.setText(planet.name + " ("+planet.production+")");
        colonize.setEnabled(canColonize);
    }

}

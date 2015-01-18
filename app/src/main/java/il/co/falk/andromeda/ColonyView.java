package il.co.falk.andromeda;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;
import il.co.falk.andromeda.game.Universe;

/**
 * Created by roy on 1/12/15.
 */
public class ColonyView extends PlanetView {
    // Data
    protected Colony colony;

    // GUI
    protected TextView rightTitle;
    protected Button attack;

    public ColonyView(final GameActivity activity, Context context, Colony c, Planet p) {
        super(context, p);

        colony = c;

        // An actual ColonyView instance and not subclass

        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.colony_layout, null);
        addView(layout);

        leftTitle = (TextView)findViewById(R.id.leftTitle);
        rightTitle = (TextView)findViewById(R.id.rightTitle);

        leftTitle.setTextColor(Color.DKGRAY);
        rightTitle.setTextColor(Color.DKGRAY);

        attack = (Button)findViewById(R.id.button_attack);
        attack.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Universe universe = Universe.getUniverse();
                universe.player.attack(planet);
                activity.repopulatePlanets();
            }
        });

    }

    public void updateView(boolean canColonize, boolean canAttack) {
        leftTitle.setText(planet.name + " (" + planet.production + ")");
        rightTitle.setText(colony.player.name);
        attack.setEnabled(canAttack);
    }
}



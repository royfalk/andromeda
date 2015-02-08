package il.co.falk.andromeda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Universe;

/**
 * Created by roy on 1/21/15.
 */
public class StarMapView extends LinearLayout {
    // GUI
    LinearLayout layout;

    // Data
    private Universe universe;

    public StarMapView(Context context, Universe universe) {
        super(context);

        this.universe = universe;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.star_map_layout, null);
        addView(layout);
    }

    public void updateView(boolean canColonize, boolean canAttack) {

    }
}



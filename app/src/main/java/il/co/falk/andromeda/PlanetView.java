package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;

/**
 * Created by roy on 1/9/15.
 */
public class PlanetView extends LinearLayout {
    // Data
    protected Planet planet;

    // GUI
    protected LinearLayout layout;
    protected TextView leftTitle;

    public PlanetView(Context context, Planet p) {
        super(context);

        planet = p;
    }

    public void updateView(Context context, boolean canColonize, boolean canAttack) {}



}

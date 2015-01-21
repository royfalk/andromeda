package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Unit;

/**
 * Created by roy on 1/21/15.
 */
public class ShipsView extends LinearLayout {
    Planet planet;
    ArrayList<UnitButton> buttons;

    public ShipsView(Context context, Planet planet) {
        super(context);

        this.planet = planet;
        updateView(context);
    }

    public void updateView(Context context) {
        removeAllViews();
        buttons = new ArrayList<>();

        for(final Unit u : planet.units) {
            UnitButton b = new UnitButton(context, u);
            addView(b);
            buttons.add(b);
        }
    }
}

class UnitButton extends ToggleButton {
    Unit unit;
    public UnitButton(Context context, Unit u) {
        super(context);

        unit = u;

        setText(u.name);
        setEnabled(false);
    }
}
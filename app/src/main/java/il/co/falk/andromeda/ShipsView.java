package il.co.falk.andromeda;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Ship;

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

        for(final Ship u : planet.ships) {
            UnitButton b = new UnitButton(context, u);
            addView(b);
            buttons.add(b);
        }
    }
}

class UnitButton extends ToggleButton {
    Ship ship;
    public UnitButton(Context context, Ship u) {
        super(context);

        ship = u;


        setChecked(false);
        setText(u.name);
        if(ship.move==0)
            setEnabled(false);
        setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //setChecked(!isChecked());
                setText(ship.name);
            }
        });
    }
}
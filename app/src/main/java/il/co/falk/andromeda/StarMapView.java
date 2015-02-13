package il.co.falk.andromeda;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Universe;

/**
 * Created by roy on 1/21/15.
 */
public class StarMapView  {
    ArrayList<TextView> planetLabels;

    public StarMapView(Context context, Universe universe, RelativeLayout relativeLayout, int width, int height) {
        planetLabels = new ArrayList<>();

        for(Planet p : universe.planets) {
            TextView tv = new TextView(context);
            tv.setText(p.name);
            relativeLayout.addView(tv);
            float x = width/104 * (1+p.location.x);
            float y = height/104 * (1+p.location.y);
            tv.setX(x);
            tv.setY(y);
            planetLabels.add(tv);
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView)v;
                    Log.d("clickclick", (String)tv.getText());
                }
            });

        }
    }
}



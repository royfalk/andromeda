package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;

/**
 * Created by roy on 1/12/15.
 */
public class PlayerColonyView extends PlanetView {
    // Data
    protected Colony colony;
    private GameActivity gameActivity;

    // GUI
    protected TextView rightTitle;
    private TextView subtitle;
    private Button produce;
    private ShipsView shipsView;


    public PlayerColonyView(final GameActivity activity, Context context, Colony c, Planet p) {
        super(context,p);
        colony = c;

        gameActivity = activity;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        layout = (LinearLayout) inflater.inflate(R.layout.player_colony_layout, null);
        addView(layout);

        leftTitle = (TextView)findViewById(R.id.leftTitle);
        rightTitle = (TextView)findViewById(R.id.rightTitle);
        subtitle = (TextView)findViewById(R.id.subtitle);
        produce = (Button)findViewById(R.id.produce);



        produce.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                activity.activeColony = colony;
                Intent intent = new Intent(gameActivity.getApplicationContext(), SelectProductActivity.class);
                //gameActivity.startActivity(intent);
                gameActivity.startActivityForResult(intent,activity.SELECT_PRODUCT);
            }
        });

        leftTitle.setTextColor(Color.DKGRAY);
        rightTitle.setTextColor(Color.DKGRAY);
        subtitle.setTextColor(Color.DKGRAY);

        shipsView = new ShipsView(context, planet);
        layout.addView(shipsView);
    }

    public void updateView(Context context, boolean canColonize, boolean canAttack) {
        leftTitle.setText(planet.name + " (" + planet.production + ")");
        rightTitle.setText(colony.player.getName());
        subtitle.setText(colony.getRemainingTurns()+" turns for ");
        produce.setText(colony.currentlyBuilding);

        if(shipsView!=null)
            shipsView.updateView(context);


    }
}

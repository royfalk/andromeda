package il.co.falk.andromeda;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import il.co.falk.andromeda.game.Game;
import il.co.falk.andromeda.game.TechManager;


public class TechActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);

        TechManager techManager = Game.INSTANCE.getHumanPlayer().getTechManager();

        TextView textView = (TextView) findViewById(R.id.techLevelResearch);
        textView.setText(String.valueOf(techManager.getTechLevel(TechManager.Field.RESEARCH)));
        textView = (TextView) findViewById(R.id.techRemainingResearch);
        textView.setText(String.valueOf(techManager.getRemaining(TechManager.Field.RESEARCH)));

        textView = (TextView) findViewById(R.id.techLevelManufacturing);
        textView.setText(String.valueOf(techManager.getTechLevel(TechManager.Field.MANUFACTURING)));
        textView = (TextView) findViewById(R.id.techRemainingManufacturing);
        textView.setText(String.valueOf(techManager.getRemaining(TechManager.Field.MANUFACTURING)));

        textView = (TextView) findViewById(R.id.techLevelArmor);
        textView.setText(String.valueOf(techManager.getTechLevel(TechManager.Field.ARMOR)));
        textView = (TextView) findViewById(R.id.techRemainingArmor);
        textView.setText(String.valueOf(techManager.getRemaining(TechManager.Field.ARMOR)));

        textView = (TextView) findViewById(R.id.techLevelBeam);
        textView.setText(String.valueOf(techManager.getTechLevel(TechManager.Field.BEAM)));
        textView = (TextView) findViewById(R.id.techRemainingBeam);
        textView.setText(String.valueOf(techManager.getRemaining(TechManager.Field.BEAM)));

        textView = (TextView) findViewById(R.id.techLevelMissile);
        textView.setText(String.valueOf(techManager.getTechLevel(TechManager.Field.MISSILE)));
        textView = (TextView) findViewById(R.id.techRemainingMissile);
        textView.setText(String.valueOf(techManager.getRemaining(TechManager.Field.MISSILE)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tech, menu);
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
}





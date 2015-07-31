package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import il.co.falk.andromeda.game.TechManager;
import il.co.falk.andromeda.game.Universe;


public class TechActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);

        TechManager techManager = Universe.getUniverse().player.techManager;

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





package il.co.falk.andromeda;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import il.co.falk.andromeda.game.ProductFactory;


public class SelectProductActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        LinearLayout layout = (LinearLayout)findViewById(R.id.product_layout);

        ProductFactory factory = ProductFactory.getFactory();
        ArrayList<String> unitTypes = factory.list();

        for(final String u : unitTypes) {
            Log.d(u,u);
            Button b = new Button(this);
            b.setText(u);
            layout.addView(b);
            b.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i=new Intent();
                    i.putExtra("product",u);
                    setResult(RESULT_OK,i);
                    finish();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_product, menu);
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

    /*public void selectMissileBase(View view) {
        Intent i=new Intent();
        i.putExtra("product",0);
        setResult(RESULT_OK,i);
        finish();
    }

    public void selectDestroyer(View view) {
        Intent i=new Intent();
        i.putExtra("product",1);
        setResult(RESULT_OK,i);
        finish();
    }

    public void selectColonyShip(View view) {
        Intent i=new Intent();
        i.putExtra("product",2);
        setResult(RESULT_OK,i);
        finish();
    }*/
}

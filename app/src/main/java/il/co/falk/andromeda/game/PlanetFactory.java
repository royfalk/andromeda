package il.co.falk.andromeda.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by roy on 4/23/15.
 */
public class PlanetFactory {

    private static PlanetFactory planetFactory;
    private ArrayList<String> planetNames;

    public PlanetFactory(Context context) {
        planetNames = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("planets");
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while((line = in.readLine()) != null) {
                planetNames.add(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        planetFactory = this;
    }

    public static String getPlanetName() {
        if(planetFactory == null) return null;
        Random r = new Random();
        int i = r.nextInt(planetFactory.planetNames.size());
        String s = planetFactory.planetNames.remove(i);
        return s;
    }


}

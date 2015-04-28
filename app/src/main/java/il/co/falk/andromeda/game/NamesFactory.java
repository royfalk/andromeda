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
public class NamesFactory {

    private static NamesFactory namesFactory;
    private ArrayList<String> planetNames;
    private ArrayList<String> racesNames;

    public NamesFactory(Context context) {
        planetNames = new ArrayList<>();
        racesNames = new ArrayList<>();

        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("planets");
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while((line = in.readLine()) != null) {
                planetNames.add(line);
            }

            is = assetManager.open("races");
            in = new BufferedReader(new InputStreamReader(is));
            line = null;

            while((line = in.readLine()) != null) {
                racesNames.add(line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        namesFactory = this;
    }

    // TODO: add check not empty
    public static String getPlanetName() {
        if(namesFactory == null) return null;
        Random r = new Random();
        int i = r.nextInt(namesFactory.planetNames.size());
        String s = namesFactory.planetNames.remove(i);
        return s;
    }

    public static String getRaceName() {
        if(namesFactory == null) return null;
        Random r = new Random();
        int i = r.nextInt(namesFactory.racesNames.size());
        String s = namesFactory.racesNames.remove(i);
        return s;
    }


}

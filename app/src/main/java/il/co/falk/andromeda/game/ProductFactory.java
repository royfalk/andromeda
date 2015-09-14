package il.co.falk.andromeda.game;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by roy on 1/17/15.
 */
public class ProductFactory {
    private static ProductFactory productFactory;
    private ArrayList<String> unitList;
    private JSONArray jsonArray;

    private ProductFactory(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("units.json");
            String jsonString = readString(is);
            jsonArray = new JSONArray(jsonString);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ProductFactory getUnitFactory(Context context) {
        if(productFactory == null) productFactory = new ProductFactory(context);
        return productFactory;
    }

    public static ProductFactory getProductFactory() {
        return productFactory;
    }

    public Unit getProduct(String unitName, Location location, Player player) {
        try {
            for(int i=0;i<jsonArray.length();i++) {
                Log.d("JSON", "Array Object");
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("name");
                if(!name.equals(unitName)) continue;

                int move = object.getInt("move");
                int hp = object.getInt("hp");
                int attack = object.getInt("attack");
                int cost = object.getInt("cost");
                Unit u = new Unit(name, move, hp, attack, cost, location, player);
                Universe.getUniverse().units.add(u);
                return u;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getProductPrice(String unitName) {
        try {
            for(int i=0;i<jsonArray.length();i++) {
                Log.d("JSON", "Array Object");
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("name");
                if(!name.equals(unitName)) continue;
                return object.getInt("cost");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<String> list(Context context) {
        try {
            ArrayList<String> list = new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String name = object.getString("name");
                list.add(name);
            }
            return list;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> list(Context context, String filter) {
        try {
            ArrayList<String> list = new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String type = object.getString("type");
                if(!type.equals(filter)) continue;
                String name = object.getString("name");
                list.add(name);
            }
            return list;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONArray getJSONArray(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("units.json");
            String jsonString = readString(is);
            return new JSONArray(jsonString);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static String readString(InputStream is) throws IOException {
        char[] buf = new char[2048];
        Reader r = new InputStreamReader(is, "UTF-8");
        StringBuilder s = new StringBuilder();
        while (true) {
            int n = r.read(buf);
            if (n < 0)
                break;
            s.append(buf, 0, n);
        }
        return s.toString();
    }
}

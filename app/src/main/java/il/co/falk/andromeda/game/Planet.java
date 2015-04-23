package il.co.falk.andromeda.game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Planet {
    public final static int MAX_SIZE = 5, AVG_SIZE = 3;
    public final static int MAX_PROD = 5, AVG_PROD = 3;
    public int production;
    public Location location;
    public String name;
    public Colony colony;
    public ArrayList<Unit> units;

    Planet(boolean homePlanet) {
        name = PlanetFactory.getPlanetName();
        if(homePlanet) {
            production = AVG_PROD;
        } else {
            Random r = new Random();
            production = r.nextInt(MAX_PROD);
        }

        location = new Location();
        colony = null;

        units = new ArrayList<>();
    }
}

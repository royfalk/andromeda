package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Planet {
    public final static int MAX_PROD = 5, AVG_PROD = 3;
    public int production;
    public Location location;
    public String name;
    public Colony colony;
    static int c = 0;


    Planet(boolean homePlanet) {
        int a = 1900 + c++;
        name = "A"+c;
        if(homePlanet) {
            production = AVG_PROD;
        } else {
            Random r = new Random();
            production = r.nextInt(MAX_PROD);
        }

        location = new Location();
        colony = null;
    }
}

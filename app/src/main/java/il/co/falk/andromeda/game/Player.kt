package il.co.falk.andromeda.game;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by roy on 1/2/15.
 */
public class Player {
    public static final int[] PLAYER_COLORS = {
        Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN};

    static int numPlayers = 0;

    ArrayList<Planet> knownPlanets;
    public ArrayList<Colony> colonies;
    ArrayList<Unit> units;
    public int color;
    public String name;
    public TechManager techManager;

    Player() {
        colonies = new ArrayList<Colony>();
        units = new ArrayList<Unit>();
        Color c = new Color();
        name = NamesFactory.getRaceName();
        color = PLAYER_COLORS[numPlayers++];
        techManager = new TechManager();
    }

    public void nextTurn() {
        for(Colony c : colonies) {
            c.nextTurn();
        }
    }

    public boolean canColonize() {
        for(Unit u : units)
                if(u.name.equals("Colony Ship"))
                    return true;
        /*for(Colony c : colonies)
            for(Unit u : c.units)
                if(u.getClass().equals(ColonyShip.class))
                    return true;*/
        return false;
    }

    public boolean canAttack() {
        for(Unit u : units)
            if(u.name.equals("Destroyer"))
                return true;
        return false;
    }

    public void colonize(Planet planet) {
        // this really shouldn't happen
        //if(canColonize()==false) return;

        // Add colony
        Colony colony = new Colony(this, planet);
        colonies.add(colony);

        // Select a colony ship
        // TODO: remove closest ship and add travel time
        Unit colonyShip = null;
        Location l=null;
        for(Unit u : units) {
            if (u.name.equals("Colony Ship")) {
                colonyShip = u;
                l=u.location;
            }
        }



        // Remove ship from colony list
        Colony colonyWithShip = null;

        for(Colony c : colonies)
            if(c!=null && c.planet.location == l)
                colonyWithShip = c;

        // Remove ship from global list
        if(colonyShip!=null)
            units.remove(colonyShip);

        // Remove ship from colony list
        if(colonyShip!=null && colonyWithShip!=null)
            colonyWithShip.planet.units.remove(colonyShip);
    }


    public void attack(Planet planet) {
        // this really shouldn't happen
        //if(canAttack()==false) return;

        // Remove colony from other player
        Colony oldColony = planet.colony;
        Player oldPlayer = oldColony.player;
        oldPlayer.colonies.remove(oldColony);

        // Add colony
        Colony colony = new Colony(this, planet);
        colonies.add(colony);
    }
}

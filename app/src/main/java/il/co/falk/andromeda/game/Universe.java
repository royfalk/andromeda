package il.co.falk.andromeda.game;

import android.content.res.AssetManager;
import android.graphics.Rect;
import android.media.Image;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by roy on 1/3/15.
 * Merged Game into this
 */
public class Universe {
    // Singleton
    private static Universe universe;

    public static final int SIZE = 15, PLAYERS=4, HEIGHT=100, WIDTH=100;

    public ArrayList<Planet> planets;
    public ArrayList<Player> players;
    public ArrayList<Unit> units;
    public Player player;
    int turn;

    public static Universe getUniverse() {
        if(universe == null) universe = new Universe();
        return universe;
    }

    private Universe() {
        // Init must now happen in createUniverse to prevent calls to getUniverse from constructor
    }

    public void createUniverse() {
        turn = 1;

        players = new ArrayList<>();
        planets = new ArrayList<>();
        units = new ArrayList<>();

        // Create Player & home planet
        player = new Player();
        players.add(player);

        Planet p = new Planet(true);
        Colony c = new Colony(player, p);
        planets.add(p);
        player.colonies.add(c);
        // TODO: for debug
        c.changeProductToBuild("Destroyer");
        for(int i=0;i<5;i++) c.nextTurn();

        // Create other players & home planets
        for(int i=players.size();i<PLAYERS;i++) {
            AIPlayer ai = new AIPlayer();
            players.add(ai);
            p = new Planet(true);
            c = new Colony(ai, p);
            planets.add(p);
            ai.colonies.add(c);
        }

        // Create rest of universe
        for(int i=planets.size();i<SIZE;i++) {
            p = new Planet(false);
            planets.add(p);
        }
    }

    boolean locationCollision(Location l) {
        for(Planet p: planets) {
            if(p.location.isInSamePlace(l))
                return true;
        }
        return false;
    }

    public void nextTurn() {
        turn++;

        // Check if player dead
        ArrayList<Player> deadPlayers = new ArrayList<>();

        for(Player p : players) {
            if(p.colonies.size()==0)
                deadPlayers.add(p);
            else
                p.nextTurn();
        }

        for(Player p : deadPlayers) {
            players.remove(p);
        }
    }

    public boolean gameEnded() {
        if(players.size()==1) return true;
        return false;
    }

    public Planet getPlanetByName(String name) {
        for(Planet p :  planets)
            if(p.name.equals(name))
                return p;
        return null;
    }

    public Colony getColonyByName(String name) {
        for(Player p: players)
            for(Colony c :  p.colonies)
                if(c.planet.name.equals(name))
                    return c;
        return null;
    }

    public ArrayList<Planet> getPlanetsInRect(Rect rect) {
        ArrayList<Planet> planets = new ArrayList<>();

        for(Planet p :  planets)
            if(rect.contains((int)p.location.x, (int)p.location.y))
                planets.add(p);
        return planets;
    }

    public ArrayList<Planet> getPlanetsByPlayer(Player player) {
        ArrayList<Planet> planets = new ArrayList<>();

        for(Colony c :  player.colonies)
            planets.add(c.planet);
        return planets;
    }

    public ArrayList<Unit> getShipsAtLocation(Location location) {
        ArrayList<Unit> fleet = new ArrayList<>();
        for(Unit unit : units) {
            if(unit.location != null && unit.location.isInSamePlace(location))
                fleet.add(unit);
        }

        if(fleet.size()>0) return fleet;
        return null;
    }

}

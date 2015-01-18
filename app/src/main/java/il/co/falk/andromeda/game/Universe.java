package il.co.falk.andromeda.game;

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
    public Player player;
    int turn;

    public static Universe getUniverse() {
        if(universe == null) universe = new Universe();
        return universe;
    }

    private Universe() {
        turn = 1;

        players = new ArrayList<Player>();
        planets = new ArrayList<Planet>();

        // Create Player & home planet
        player = new Player();
        players.add(player);

        Planet p = new Planet(true);
        Colony c = new Colony(player, p);
        planets.add(p);
        player.colonies.add(c);

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



}

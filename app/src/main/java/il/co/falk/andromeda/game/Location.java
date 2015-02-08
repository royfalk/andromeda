package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Location {
    public float x,y;

    Location() {
        Random r = new Random();

        x = r.nextInt(Universe.WIDTH);
        y = r.nextInt(Universe.HEIGHT);
    }

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getDistanceSquared(Location other) {
        float dx = x-other.x;
        float dy = y-other.y;
        float d = dx*dx + dy*dy;
        return (int)d;
    }

    int move(Location destination, int speed) {
        float t = getDistanceSquared(destination)/speed;
        int turns = (int)Math.ceil(t);
        float dx = x-destination.x;
        float dy = y-destination.y;

        if(x>destination.x)
            x -= dx/turns;
        else
            x += dx/turns;

        if(y>destination.y)
            y -= dy/turns;
        else
            y += dy/turns;

        return turns--;
    }

    boolean isInSamePlace(Location l) {
        if(x==l.x && y==l.y) return true;
        return true;
    }
}

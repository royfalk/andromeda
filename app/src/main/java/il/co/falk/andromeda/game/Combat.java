package il.co.falk.andromeda.game;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Combat {
    ArrayList<Unit> attackers;
    ArrayList<Unit> defenders;
    Location location;

    public Combat(Location l, ArrayList<Unit> attackers, ArrayList<Unit> defenders) {
        location = l;
        this.attackers = attackers;
        this.defenders = defenders;
    }

    public Combat() {
        Location l = new Location(0,0);
        attackers = new ArrayList<Unit>();
        defenders = new ArrayList<Unit>();
        for(int i=0;i<3;i++) {
            MissileBase m = new MissileBase(l);
            defenders.add(m);

            Destroyer d = new Destroyer(l);
            defenders.add(d);
        }

        for(int i=0;i<6;i++) {
            Destroyer d = new Destroyer(l);
            attackers.add(d);
        }
    }

    public void fight() {
        Random r = new Random();

        while(attackers.size()>0 && defenders.size()>0) {
            Log.d("Andromeda", "A:" + attackers.size() + " D:" + defenders.size());
            for(Unit u : attackers) {
                int index = r.nextInt(defenders.size());
                Unit v = defenders.get(index);
                int hp = v.hp;
                u.attack(v);
                int dmg = hp - v.hp;
                Log.d("Andromeda", "HP:" + hp + " Dmg:" + dmg);
                if(v.isDead())
                    defenders.remove(index);

            }

            for(Unit u : defenders) {
                int index = r.nextInt(attackers.size());
                Unit v = attackers.get(index);
                int hp = v.hp;
                u.attack(v);
                int dmg = hp - v.hp;
                Log.d("Andromeda", "HP:" + hp + " Dmg:" + dmg);
                if(v.isDead())
                    attackers.remove(index);
            }
        }
    }

    public boolean attackSuccessful() {
        if(attackers.size()>0) return true;
        return false;
    }
}

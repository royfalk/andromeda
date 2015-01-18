package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Unit {
    int move;
    int maxHP;
    int attack;
    int hp;
    int cost;
    public Location location;
    public Location destination;


    Unit(int move, int maxHP, int attack, int cost, Location location) {
        this.move = move;
        this.maxHP = maxHP;
        this.attack = attack;
        this.cost = cost;
        hp = maxHP;
    }

    // Copy Constructor
    Unit(Unit unit) {
        this.move = unit.move;
        this.maxHP = unit.maxHP;
        this.attack = unit.attack;
        this.cost = unit.cost;
        hp = maxHP;
    }

    void attack(Unit target) {
        if(hp<=0)
            return; // Unit is dead
        Random r = new Random();
        int damage = r.nextInt(attack);
        target.hp -= damage;
    }

    public boolean isDead() {
        if(hp<=0)
            return true;
        return false;
    }

    void move(Location destination) {
        this.destination = destination;
    }

    void move() {

    }
}

package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 1/2/15.
 */
public class Unit {
    public String name;
    public int move;
    int maxHP;
    int attack;
    int hp;
    public int cost;
    public Location location;
    public Location destination;


    public Unit(String name, int move, int maxHP, int attack, int cost, Location location) {
        this.name = name;
        this.move = move;
        this.maxHP = maxHP;
        this.attack = attack;
        this.cost = cost;
        hp = maxHP;
    }

    public Unit(Unit u) {
        name = u.name;
        move = u.move;
        maxHP = u.maxHP;
        attack = u.attack;
        cost = u.cost;
        hp = u.maxHP;
        location = u.location;
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

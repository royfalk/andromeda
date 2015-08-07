package il.co.falk.andromeda.game;

import java.util.Random;

/**
 * Created by roy on 8/5/15.
 */
public class Product {
    public String name;
    public int maxHP;
    int attack;
    public int hp;
    public int cost;
    public Location location;
    public Player player;


    public Product(String name, int maxHP, int attack, int cost, Location location, Player player) {
        this.name = name;
        this.maxHP = maxHP;
        this.attack = attack;
        this.cost = cost;
        hp = maxHP;
        this.player = player;
    }

    public Product(Product u) {
        name = u.name;
        maxHP = u.maxHP;
        attack = u.attack;
        cost = u.cost;
        hp = u.maxHP;
        location = u.location;
    }



    void attack(Unit target) {
        if(attack == 0 || hp <= 0) // Unit is dead or harmless
            return;

        Random r = new Random();
        int damage = r.nextInt(attack);
        target.hp -= damage;
    }

    public boolean isDead() {
        if(hp<=0)
            return true;
        return false;
    }
}

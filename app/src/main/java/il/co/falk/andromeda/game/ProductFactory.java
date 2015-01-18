package il.co.falk.andromeda.game;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by roy on 1/17/15.
 */
public class ProductFactory {
    private static ProductFactory factory;

    Unit[] items;

    private ProductFactory() {
        Unit[] i = {new MissileBase(null),
                new Destroyer(null),
                new ColonyShip(null)};
        items = i;
    }

    public static ProductFactory getFactory() {
        if(factory == null) factory = new ProductFactory();
        return factory;
    }

    public ArrayList<String> list() {
        ArrayList<String> list = new ArrayList<>();
        for(Unit u : items) {
            list.add(u.getClass().getSimpleName());
        }
        return list;
    }

    public Unit getUnit(String name) {
        for(Unit u : items)
            if(u.getClass().getSimpleName().equals(name))
                return u;
        return null;
    }
}

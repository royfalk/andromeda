package il.co.falk.andromeda.game;

/**
 * Created by roy on 7/28/15.
 */
public class TechManager {
    public enum Field {
        RESEARCH, MANUFACTURING, ARMOR, BEAM,
        MISSILE
    }

    ATech research, manufacturing, armor, beam, missile;


    TechManager() {
        research = new ATech(0.33);
        manufacturing = new ATech(0.33);
        armor = new ATech(0.11);
        beam = new ATech(0.11);
        missile = new ATech(0.12);
    }

    public void research(int effort) {
        research.research(effort);
        manufacturing.research(effort);
        armor.research(effort);
        beam.research(effort);
        missile.research(effort);
    }

    public int getTechLevel(Field field) {
        switch (field) {
            case RESEARCH: return research.getLevel();
            case MANUFACTURING: return manufacturing.getLevel();
            case ARMOR: return armor.getLevel();
            case BEAM: return beam.getLevel();
            case MISSILE: return missile.getLevel();
            default: return -1;
        }
    }

    public int getRemaining(Field field) {
        switch (field) {
            case RESEARCH: return research.getRemaining();
            case MANUFACTURING: return manufacturing.getRemaining();
            case ARMOR: return armor.getRemaining();
            case BEAM: return beam.getRemaining();
            case MISSILE: return missile.getRemaining();
            default: return -1;
        }
    }
}

class ATech {
    private final int RESEARCH_LEVEL = 100;
    double ratio;
    int level, effort, toNextLevel;

    public ATech(double ratio) {
        this.ratio = ratio;
        level = 0;
        effort = 0;
        toNextLevel = RESEARCH_LEVEL * (int)Math.pow(2,level);
    }

    public void research(int effort) {
        this.effort += (int)(ratio * effort);

        if(toNextLevel <= this.effort) {
            this.effort -= toNextLevel;
            level++;
            toNextLevel = RESEARCH_LEVEL * (int)Math.pow(2,level);
        }
    }

    public int getLevel() { return level; }

    public int getRemaining() { return toNextLevel - this.effort; }
}
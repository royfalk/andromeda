package il.co.falk.andromeda.game;

/**
 * Created by roy on 7/28/15.
 */
public class TechManager {
    public enum Field {
        RESEARCH, MANUFACTURING, ARMOR, BEAM,
        MISSILE
    }

    Tech research, manufacturing, armor, beam, missile;


    TechManager() {
        research = new Tech(0.33);
        manufacturing = new Tech(0.33);
        armor = new Tech(0.11);
        beam = new Tech(0.11);
        missile = new Tech(0.12);
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
}

class Tech {
    private final int RESEARCH_LEVEL = 100;
    double ratio;
    int level, effort;

    public Tech(double ratio) {
        this.ratio = ratio;
        level = 0;
        effort = 0;
    }

    public void research(int effort) {
        this.effort = (int)ratio * effort;
        int toNextLevel = RESEARCH_LEVEL * 2^level;
        if(effort > toNextLevel)
            level++;
    }

    public int getLevel() { return level; }
}
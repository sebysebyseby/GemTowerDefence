package Model;

/**
 * Created by Sebastian on 2015-07-22.
 */
public class SpecialTower extends Tower {

    private String name;

    public SpecialTower(String name, String type, int xPos, int yPos,  int lowDamage, int highDamage, double range, int maxCooldown) {
        super(type, range, maxCooldown, highDamage, xPos, yPos, lowDamage);
        this.name = name;
    }

    @Override
    public void fireTower() {
        defaultShot();
    }

    public String getName(){
        return name;
    }

    @Override
    public String getURL() {
        return "Images/" + getName() + ".png";
    }

    @Override
    public int getClassNumber() {
        return 2;
    }
}

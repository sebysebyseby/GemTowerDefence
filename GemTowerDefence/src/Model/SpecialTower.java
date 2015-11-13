package Model;

/**
 * Created by Sebastian on 2015-07-22.
 */
public class SpecialTower extends Tower {

    private String name;

    public SpecialTower(String type, double range, int maxCooldown, int highDamage, int xPos, int yPos, int lowDamage) {
        super(type, range, maxCooldown, highDamage, xPos, yPos, lowDamage);
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

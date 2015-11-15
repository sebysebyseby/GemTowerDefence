package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Sebastian on 2015-07-22.
 */
public class Gem extends Tower{

    private String grade;
    private Game game;
    private List<Gem> gemsToCombineWith;
    private String upgradeSpecialTowerName;

    // Constructor: create new gem, all inputs need to be given, only kills and level are set initially to 0

    public Gem(String type, String grade, int xPos, int yPos, int lowDamage,
               int highDamage, double range, int maxCooldown) {
        super(type, range, maxCooldown, highDamage, xPos, yPos, lowDamage);
        this.grade = grade;
        retrieveUpgradeDetails();
        game = Game.getInstance();
    }

    public void retrieveUpgradeDetails(){
        //todo: make method to instantiate the gems it can comebine with and the upgrade specialTower's name.
    }

    // Getters and Setters:
    public int getLowDamage() {
        return lowDamage;
    }

    @Override
    public void fireTower() {
        switch (type) {
            case "Emerald":
                type = "Emerald";
                poisonShot(2, 5);
                break;
            case "Ruby":
                type = "Ruby";
                splashShot(30);
                break;
            case "Sapphire":
                type = "Sapphire";
                slowShot(25, 5);
                break;
            case "Topaz":
                multiShot(3);
                break;
            case "Diamond":
                type = "Diamond";
                criticalShot(33, 5);
                break;
            case "Aquamarine":
                defaultShot();
                break;
            case "Opal":
                defaultShot();
                break;
            case "Amethyst":
                defaultShot();
                break;
            default:
        }
    }

    public String getGrade(){
        return grade;
    }
    public void setGrade(String grade) { this.grade = grade; }
    public String getType() { return type; }

    @Override
    public String getURL() {
        return "Images/" + getGrade() + getType() + ".png";
    }

    @Override
    public int getClassNumber() {
        return 1;
    }
}

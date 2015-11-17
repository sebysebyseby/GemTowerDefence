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
    private List<Gem> otherGemsToCombineWith; // this is a list of the gems (not including this one) needed to combine with
    private SpecialTower upgradeTower; // The special tower it combines into

    // Constructor: create new gem, all inputs need to be given, only kills and level are set initially to 0

    public Gem(String type, String grade, int xPos, int yPos, int lowDamage,
               int highDamage, double range, int maxCooldown) {
        super(type, range, maxCooldown, highDamage, xPos, yPos, lowDamage);
        this.grade = grade;
        otherGemsToCombineWith = new LinkedList<>();
        game = Game.getInstance();
        retrieveUpgradeDetails(); // this has to be called later to avoid referencing gems that don't exist yet on load

    }

    public Gem(String type, String grade, int xPos, int yPos, int lowDamage,
               int highDamage, double range, int maxCooldown, boolean hasStartUpCompleted) {
        super(type, range, maxCooldown, highDamage, xPos, yPos, lowDamage);
        this.grade = grade;
        otherGemsToCombineWith = new LinkedList<>();
        game = Game.getInstance();
        if (hasStartUpCompleted) retrieveUpgradeDetails(); // this has to be called later to avoid referencing gems that don't exist yet on load

    }

    // Call a static method to return a list with the tower this gem combine special 's into,
    // and the gems needed to combine into that tower, in that order
    // ASSUMES that the list produced by the static method is correctly structured
    // if a null completeInfo list is given, it implies that this gem has no upgrade path
    public void retrieveUpgradeDetails(){
        List<Tower> completeInfo = Constants.getUpgradeGems(grade + type);
        if (completeInfo != null) {
            upgradeTower = (SpecialTower) completeInfo.get(0);
            for (int i = 1; i < completeInfo.size(); i++) {
                Gem g = (Gem) completeInfo.get(i);
                if (!g.getGrade().equals(grade) || !g.getType().equals(type)) {
                    otherGemsToCombineWith.add(g);
                }
            }
        }

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
                slowShot(100, 40);
                break;
            case "Topaz":
                multiShot(3);
                break;
            case "Diamond":
                type = "Diamond";
                criticalShot(33, 5);
                break;
            case "Aquamarine":
                stunShot(100, 10);
                break;
            case "Opal":
                stunShot(100, 10);
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

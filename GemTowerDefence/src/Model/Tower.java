package Model;

import sun.awt.image.ImageWatched;

import java.awt.*;
import java.lang.annotation.Target;
import java.util.*;

/**
 * Created by Sebastian on 2015-07-22.
 */
public abstract class Tower extends Sprite {
    protected String type;
    protected int xPos;
    protected int yPos;
    protected int lowDamage;
    protected int highDamage;
    protected int kills;
    protected int level;
    protected double range;
    protected int currentCooldown;
    protected int maxCooldown;
    protected Enemy target;

    private Game game = Game.getInstance();

    public Tower(String type, double range, int maxCooldown, int highDamage, int xPos, int yPos, int lowDamage) {
        this.type = type;
        this.range = range;
        this.maxCooldown = maxCooldown;
        this.highDamage = highDamage;
        this.xPos = xPos;
        this.yPos = yPos;
        this.lowDamage = lowDamage;
        this.currentCooldown = maxCooldown;
        this.kills = 0;
        this.level = 0;
        this.target = null;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getxPos() {
        return xPos;
    }
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    public int getLowDamage() {
        return lowDamage;
    }
    public void setLowDamage(int lowDamage) {
        this.lowDamage = lowDamage;
    }
    public int getHighDamage() {
        return highDamage;
    }
    public void setHighDamage(int highDamage) {
        this.highDamage = highDamage;
    }
    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public double getRange() {
        return range;
    }
    public void setRange(double range) {
        this.range = range;
    }
    public int getCurrentCooldown() {
        return currentCooldown;
    }
    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }
    public int getMaxCooldown() {
        return maxCooldown;
    }
    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }
    public Enemy getTarget(){ return target; }
    public void setTarget(Enemy target) { this.target = target; }


    public Point getPoint() { return new Point(xPos, yPos); }

    public void updateCooldown(){
        if (currentCooldown > 0) {
            currentCooldown -= 1;
        }
    }

    public void resetCooldown(){
        this.currentCooldown = 0;
    }

    /*
    * For each enemy in "numTargets" or targets in a splash radius do the following:
    * Fires tower; target's health is reduced by the tower's damage
    * Has a chance to do "multiplier" times damage at with "chance" probability
    * Affect enemies with statuses like poison or slow etc if their condition is passed as true
    * Creates a line to represent the tower's shot
    * Kills increases by 1 if the target is killed (alive before shooting, dead after shooting)
    * If the target killed was the game's "selected" set selected to null
    * */
    public void shot(boolean slow, int slowDuration, boolean poison, int damagePerTick, int poisonDuration, boolean splash, int splashRadius, boolean crit, int chance, int multiplier, int numTargets){
        java.util.List<Enemy> enemies = game.getSortedEnemiesInRangeOf(this);
        for (int i = 0; i<enemies.size() && i<numTargets; i++){ //repeat the shot cycle numTarget times
            setTarget(enemies.get(i));

            game.getLines().add(new Line(xPos + 10, yPos + 10, target.getxPos() + 10, target.getyPos() + 10, type));
            int damage = this.getDamage();
            if (crit) if (Math.floor(Math.random())*100 <= chance-1) damage = damage * multiplier; //Crit
            boolean aliveBeforeShooting = target.getCurrentHP() > 0;
            target.setCurrentHP(target.getCurrentHP() - damage);
            boolean aliveAfterShooting = target.getCurrentHP() > 0;
            if (aliveBeforeShooting && !aliveAfterShooting) {
                kills += 1;
                if (game.getSelected() == target) game.setSelected(null); //deselect a dead target
            }
            if (splash){
                LinkedList<Enemy> splashTargets = new LinkedList<>();
                double dx;
                double dy;
                for (Enemy next : game.getEnemies()){
                    dy = target.getxPos() - next.getxPos();
                    dx = target.getyPos() - next.getyPos();
                    if (Math.sqrt(dx*dx + dy*dy) <= splashRadius && target != next){
                        splashTargets.add(next);
                    }
                }
                for (Enemy e: splashTargets){
                    game.getLines().add(new Line(target.getxPos() + 10, target.getyPos() + 10, e.getxPos() + 10, e.getyPos() + 10 , type)); //add an appropriate line
                    aliveBeforeShooting = e.getCurrentHP() > 0;
                    e.setCurrentHP(e.getCurrentHP() - damage);
                    aliveAfterShooting = e.getCurrentHP() > 0;
                    if (aliveBeforeShooting && !aliveAfterShooting) {
                        kills += 1; // award a kill to the tower that dealt the killing damage
                        if (game.getSelected() == target) game.setSelected(null); //deselect a dead target
                    }
                }
            }

        }
    }

    public void defaultShot(){
        shot(false, 0, false, 0, 0, false, 0, false, 0, 0, 1);
    }
    public void multiShot(int numTargets){
        shot(false, 0, false, 0, 0, false, 0, false, 0, 0, numTargets);
    }
    public void poisonShot(int damagePerTick, int duration){
        shot(false, 0, true, damagePerTick, duration, false, 0, false, 0, 0, 1);
    }
    public void slowShot(int duration){
        shot(true, duration, false, 0, 0, false, 0, false, 0, 0, 1);
    }
    public void criticalShot(int chance, int multiplier){
        shot(false, 0, false, 0, 0, false, 0, true, chance, multiplier, 1);
    }
    public void splashShot(int splash){
        shot(false, 0, false, 0, 0, true, splash, false, 0, 0, 1);
    }

    public abstract void fireTower();

    /*
    * Retuns a damage randomly generated between the maximum damage and the minimum damage
    */
    public int getDamage(){
        double baseDamage = (double) getLowDamage();
        double maxExtraDamage = (double) getHighDamage() - getLowDamage();
        double rng = Math.random();
        return (int) Math.round(baseDamage + maxExtraDamage * rng); // min damage + (damage gap * randomly between 0-1)
    }

    /*
    * Returns a string representing the URL of the Tower's image, in the Images folder.
    * */

    public abstract String getURL();

    // Two towers are considered equal if they are in the same spot on the map (same x and y position)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tower tower = (Tower) o;

        if (xPos != tower.xPos) return false;
        return yPos == tower.yPos;

    }

    @Override
    public int hashCode() {
        int result = xPos;
        result = 31 * result + yPos;
        return result;
    }
}

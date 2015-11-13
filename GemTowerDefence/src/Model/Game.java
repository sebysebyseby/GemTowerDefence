package Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Sebastian on 2015-07-22.
 */
public class Game {

    private static Game theGame;
    private List<Tower> towers;
    private List<Rock> rocks;
    private List<Enemy> enemies;
    private int enemiesMadeThisWave = 0;
    private int framesUntilNextEnemySpawn = 150; //todo
    private int level;
    private int lives;
    private int gold;
    private List<Line> lines;
    private List<Gem> last5Built;
    private Sprite selected;
    private int chippedChance;
    private int flawedChance;
    private int normalChance;
    private int flawlessChance;
    private int perfectChance;
    private int difficulty = 1;
    private boolean isWaveOver;
    private boolean isTowerPlacingMode;
    private boolean isGameOver;
    private boolean isTowerPlacingModePaused;




    /**
     * Constructor for the game as a singleton
     */

    private Game(){
        this.towers = new LinkedList<>();
        this.enemies = new LinkedList<>();
        this.lives = 10;
        this.gold = 0;
        this.lines = new LinkedList<>();
        this.rocks = new LinkedList<>();
        this.level = 1;
        this.last5Built = new LinkedList<>();
        this.chippedChance = 100;
        this.flawedChance = 0;
        this.normalChance = 0;
        this.flawlessChance = 0;
        this.perfectChance = 0;
        this.isWaveOver = true;
        this.isGameOver = false;
        this.isTowerPlacingMode = true;
        this.isTowerPlacingModePaused = true;
    }

    // Returns the instance of the game
    public static Game getInstance(){
        if (theGame == null){
            theGame = new Game();
        }
        return theGame;
    }

    //Getters and Setters
    public List<Tower> getTowers() {
        return towers;
    }
    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    public List<Rock> getRocks(){ return this.rocks; }
    public int getLevel() { return level; }
    public void setLevel(int level){ this.level = level; }
    public int getLives () { return lives; }
    public void setLives (int lives) { this.lives = lives; }
    public int getGold (){ return gold; }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public List<Line> getLines() {
        return lines;
    }
    public List<Gem> getLast5Built() {
        return last5Built;
    }
    public void setLast5Built(List<Gem> last5Built) {
        this.last5Built = last5Built;
    }
    public Sprite getSelected() {
        return selected;
    }
    public void setSelected(Sprite selected) {
        this.selected = selected;
    }
    public int getPerfectChance() {
        return perfectChance;
    }
    public void setPerfectChance(int perfectChance) {
        this.perfectChance = perfectChance;
    }
    public int getFlawlessChance() {
        return flawlessChance;
    }
    public void setFlawlessChance(int flawlessChance) {
        this.flawlessChance = flawlessChance;
    }
    public int getNormalChance() {
        return normalChance;
    }
    public void setNormalChance(int normalChance) {
        this.normalChance = normalChance;
    }
    public int getFlawedChance() {return flawedChance; }
    public void setFlawedChance(int flawedChance) {
        this.flawedChance = flawedChance;
    }
    public int getChippedChance() {
        return chippedChance;
    }
    public void setChippedChance(int chippedChance) {
        this.chippedChance = chippedChance;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void setIsWaveOver(boolean isWaveOver) {
        this.isWaveOver = isWaveOver;
    }
    public boolean getIsGameOver() {
        return isGameOver;
    }
    public void setIsTowerPlacingMode(boolean isTowerPlacingMode) {
        this.isTowerPlacingMode = isTowerPlacingMode;
    }
    public boolean isTowerPlacingMode() {return isTowerPlacingMode;}
    public boolean isTowerPlacingModePaused() {
        return isTowerPlacingModePaused;
    }
    public void setIsTowerPlacingModePaused(boolean isTowerPlacingModePaused) {
        if (isTowerPlacingModePaused)
            setSelected(null);
        this.isTowerPlacingModePaused = isTowerPlacingModePaused;
    }

    // Returns a randomly generated gem. If there are new gem types, this method will need to be edited to support them.
    public Gem newRandomGem(){
        int types = Constants.numOfGemTypes;

        String grade = null;
        String type = null;

        int result = (int) Math.ceil(100 * Math.random());
        if (result <= getChippedChance()){
            grade = "Chipped";
            result = 200;  //sets the result to 200 so that none of the other cases trigger
        }
        else result -= getChippedChance();
        if (result <= getFlawedChance()){
            grade = "Flawed";
            result = 200;
        }
        else result -= getFlawedChance();
        if (result <= getNormalChance()){
            grade = "Normal";
            result = 200;
        }
        else result -= getFlawlessChance();
        if (result <= getFlawlessChance()){
            grade = "Flawless";
            result = 200;
        }
        else result -= getFlawlessChance();
        if (result <= getPerfectChance()){
            grade = "Perfect";
            result = 200;
        }

        result = (int) Math.ceil(types * Math.random());
        switch (result){
            case 1: type = "Emerald";
                break;
            case 2: type = "Ruby";
                break;
            case 3: type = "Sapphire";
                break;
            case 4: type = "Topaz";
                break;
            case 5: type = "Diamond";
                break;
            case 6: type = "Aquamarine";
                break;
            case 7: type = "Opal";
                break;
            case 8: type = "Amethyst";
                break;
            default: break;
        }

        for (Gem gem: Constants.allPossibleGems){
            if (gem.getType().equals(type) && gem.getGrade().equals(grade)){
//                gem.setxPos((int) Math.ceil(190 * Math.random()));
//                gem.setyPos((int) Math.ceil(190 * Math.random()));
                return gem;
            }
        }
        return null;
    }

    // Gets the set of Gems and Special Towers in range
    //EFFECTS: returns the set of towers and gems in range, returns nothing if no towers/gems in range
    public List<Tower> getTowersInRangeOf(Tower tower) {
        List<Tower> towersInRange = new LinkedList<Tower>();
        double dx;
        double dy;
        for (Tower next : towers){
            dy = next.getxPos() - tower.getxPos();
            dx = next.getxPos() - tower.getxPos();
            if (Math.sqrt(dx*dx + dy*dy) <= tower.getRange()){
                towersInRange.add(next);
            }
        }
        return towersInRange;
    }

    // Gets the set of Enemies in range
    //EFFECTS: returns the set of towers and gems in range, returns an empty list if no towers/gems in range
    public List<Enemy> getEnemiesInRangeOf(Tower tower) {
        List<Enemy> enemiesInRange = new LinkedList<>();
        double dx;
        double dy;
        for (Enemy next : enemies){
            dy = tower.getxPos() - next.getxPos();
            dx = tower.getyPos() - next.getyPos();
            if (Math.sqrt(dx*dx + dy*dy) <= tower.getRange()){
                enemiesInRange.add(next);
            }
        }
        return enemiesInRange;
    }

    // finds the closest enemy to the point clicked, as long as it is within seleectionRadius distance
    // the + 5 in this case is to account for the fact that enemies have their x and y position at the
    // upper left corner, adding 5 will center the x and y position on the center of the enemy image
    // SETS selected to null if it finds a close enemy, or null if there is none.
    public void setSelectedToEnemyNearPoint(Point p){
        double dx;
        double dy;
        int selectionRadius = 10;
        Enemy nearest = null;
        for (Enemy enemy: enemies){
            dx = enemy.getxPos() - p.getX() + 5;
            dy = enemy.getyPos() - p.getY() + 5;
            if (Math.sqrt(dx*dx + dy*dy) <= selectionRadius){
                nearest = enemy;
            }
        }
        selected = nearest;
    }

    // produce a list of enemies in range sorted by lowest HP first, most HP last
    public List<Enemy> getSortedEnemiesInRangeOf(Tower t){
        LinkedList<Enemy> sortedEnemiesInRange = new LinkedList<>();
        for (Enemy e: getEnemiesInRangeOf(t)){
            int i = 0;
            while (i<sortedEnemiesInRange.size() && e.getCurrentHP() >= sortedEnemiesInRange.get(i).getCurrentHP()){
                i++;
            }
            sortedEnemiesInRange.add(i, e);
        }
        return sortedEnemiesInRange;
    }

    public void updateGame(){
        makeEnemy();
        updateTowerTargets();
        fireTowers();
        for (int i=0; i<5; i++){
            moveEnemies();
        }
        updateCooldowns();
        isWaveOver();
        isGameOver();
    }

    // makes an enemy if 10 haven't been created yet
    public void makeEnemy(){
        if (enemiesMadeThisWave < 10 && framesUntilNextEnemySpawn <= 0){
            enemies.add(new Enemy());
            enemiesMadeThisWave += 1;
            framesUntilNextEnemySpawn = getFramesUntilNextEnemySpawn(difficulty, level);
        } else framesUntilNextEnemySpawn -= 1;
    }

    // updates the target of all towers in the game.
    // the target seleccted will be the one with the lowest health. In a tie, the enemy that spawned first is targeted
    public void updateTowerTargets(){
        for (Tower t: towers) {
            List<Enemy> enemiesInRange = getSortedEnemiesInRangeOf(t);
            Enemy target = null;
            for (Enemy e: enemiesInRange){
                if (target == null){
                    target = e;
                }
                if (e.getCurrentHP() < target.getCurrentHP()){
                    target = e;
                }
            }
            t.setTarget(target);
        }
    }

    // when the cooldown of a tower gets to 0, the tower may fire
    public void fireTowers(){
        for (Tower tower: towers){
            if (tower.getCurrentCooldown() == 0
                    && tower.getTarget() != null){
                tower.fireTower();
                tower.setCurrentCooldown(tower.maxCooldown);
            }
        }
    }

    public void moveEnemies(){
        //make sure that if an enemy moves off the screen (to the right or bottom of the screen) it "leaked".
        // if a creep leaks and there are no lives left then the game is lost.
        // check that if there are no more enemies, wave is over.
        // if an enemy has 0 or less health, instead of moving it is removed and gold is awarded for its lives cost

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()){
            Enemy e = enemyIterator.next();
            e.moveEnemy();
            if (e.getCurrentHP() <= 0){
                enemyIterator.remove();
                gold += e.getLivesCost();
            } else if (e.getxPos() >= (Constants.NUM_TILES_WIDE * Constants.TILE_SIZE) ||
                    e.getyPos() >= (Constants.NUM_TILES_HIGH * Constants.TILE_SIZE) ){
                enemyIterator.remove(); // for safe modification of lists
                lives -= e.getLivesCost();
            }
        }
    }

    public void updateCooldowns() {
        for (Tower next: towers){
            if (isWaveOver) {
                next.resetCooldown();
            }
            else next.updateCooldown();
        }
    }


    public boolean isGameOver(){
        if (lives <= 0) isGameOver = true;
        return isGameOver;
    }

    //WAVE ENDS WHEN ALL ENEMIES DIE OR LEAK
    public boolean isWaveOver(){
        if (enemies.size() == 0 && enemiesMadeThisWave == 10){
            isWaveOver = true;
            isTowerPlacingMode = true;
            isTowerPlacingModePaused = true;
            enemiesMadeThisWave = 0;
            level += 1;
            //todo add gold bonus for clearing a wave?
        }
        return isWaveOver;
    }


    public int getFramesUntilNextEnemySpawn(int difficulty, int level){
        return 20; //todo
    }

}

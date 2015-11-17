package Model;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebastian on 2015-07-22.
 */
public class Enemy extends Sprite{

    private int maxHP;
    private int currentHP;
    private int livesCost;
    private int xPos;
    private int yPos;
    private List<Status> statuses;
    private int currentSpeed;
    private int maxSpeed;
    private int pause;
//    private Tile currentTile;
//    private Tile destinationTile;
    private int destinationCheckpoint;
    private Point[] checkpointList;
//    private List<Tile> destinationTileList;
    private char direction = 'R';
    private Game game = Game.getInstance();
    private Grid grid  = Grid.getInstance();


    /*
    * Constructor for an enemy; creates an enemy based on information from a todo //different class//
    */
    public Enemy(){
        checkpointList = grid.getCheckpointList();
        maxHP = getMaxHp();
        currentHP = maxHP;
        livesCost = Constants.getLivesCost(game);
        xPos = (int) checkpointList[0].getX();
        yPos = (int) checkpointList[0].getY();
        statuses = new LinkedList<>();
        maxSpeed = Constants.getMaxSpeed(game);
        pause = maxSpeed;
        currentSpeed = maxSpeed;
        destinationCheckpoint = 0;
//        destinationTileList = Constants.getEnemyRoute();
//        destinationTile = destinationTileList.get(0);
    }


    //Getters and Setters
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public int getLivesCost() {
        return livesCost;
    }
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
    public int getyPos() {
        return yPos;
    }
    public int getxPos() {
        return xPos;
    }
    public List<Status> getStatuses(){ return statuses; }
    public int getMaxHp(){
        return Constants.getEnemyMaxHP(game.getDifficulty(), game.getLevel());
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }
    public int getCurrentSpeed() {
        return currentSpeed;
    }
    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }


    public void tickStatuses(){
        Iterator<Status> si = statuses.iterator();
        while (si.hasNext()){
            Status s = si.next();
            if (s.getDurationLeft() <= 0){
                si.remove();
            } else {
                s.tickStatus();
            }
        }
    }

    Status stun = new Status("Stun", 0,0,0,null,null);
    // move the enemy based on its current speed, if it is not stunned
    public void moveEnemy(){
        if (pause <= 0 && !statuses.contains(stun)) {
            if (xPos == (int) checkpointList[destinationCheckpoint].getX() &&
                    yPos == (int) checkpointList[destinationCheckpoint].getY()) {
                if (checkpointList[destinationCheckpoint + 1] == null) direction = 'R';
                else {
                    destinationCheckpoint += 1;
                    int destinationX = (int) checkpointList[destinationCheckpoint].getX();
                    int destinationY = (int) checkpointList[destinationCheckpoint].getY();
                    if (xPos < destinationX) direction = 'R';
                    if (xPos > destinationX) direction = 'L';
                    if (yPos > destinationY) direction = 'U';
                    if (yPos < destinationY) direction = 'D';
                }
            }
            // updates the enemy's x and y coordinates
            switch (direction) {
                case 'U':
                    yPos -= 1;
                    break;
                case 'D':
                    yPos += 1;
                    break;
                case 'L':
                    xPos -= 1;
                    break;
                case 'R':
                    xPos += 1;
                    break;
            }
            pause = currentSpeed;
        }
        pause--;
    }

    /*
    // updates the current tile
    // moves an enemy towards its destination tile. Updates the new destination tile upon getting there.
    // if the enemy gets to the last checkpoint its direction is set to right by default so that it goes off the screen
    // an enemy is considered on a new tile AS SOON AS it steps on the very edge of a new tile. Later I should make it get to the middle of a tile before updating
    public void moveEnemy(){ // todo!!!
//        currentTile = grid.getTileAtPoint(Grid.snapToGrid(new Point(xPos,yPos)));
//        if (currentTile.equals(destinationTile)) {
        if (xPos == (int) checkpointList[destinationCheckpoint].getX() &&
                yPos == (int) checkpointList[destinationCheckpoint].getY()) {
            if (checkpointList[destinationCheckpoint + 1] == null) direction = 'R';
//            if (destinationTileList.get(destinationTileList.indexOf(destinationTile) + 1) == null){
//                direction = 'R';
            else {
//                destinationTile = destinationTileList.get(destinationTileList.indexOf(destinationTile) + 1);
                destinationCheckpoint += 1;
//                int currentX = currentTile.getxTilePos();
//                int currentY = currentTile.getyTilePos);
//                int destinationX = destinationTile.getxTilePos();
//                int destinationY = destinationTile.getyTilePos();
                int destinationX = (int) checkpointList[destinationCheckpoint].getX();
                int destinationY = (int) checkpointList[destinationCheckpoint].getY();
                if (xPos < destinationX) direction = 'R';
                if (xPos > destinationX) direction = 'L';
                if (yPos > destinationY) direction = 'U';
                if (yPos < destinationY) direction = 'D';
            }
        }
        // updates the enemy's x and y coordinates
        switch (direction) {
            case 'U':
                yPos -= 1;
                break;
            case 'D':
                yPos += 1;
                break;
            case 'L':
                xPos -= 1;
                break;
            case 'R':
                xPos += 1;
                break;
        }

    }
    */

    @Override
    public int getClassNumber() {
        return 3;
    }

    @Override
    public Point getPoint() {
        return null;
    }
}

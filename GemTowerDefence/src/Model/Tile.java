package Model;

import java.awt.*;

/**
 * Created by Sebastian on 2015-09-01.
 */
public class Tile {

    private char tile;
    private boolean isEmpty;
    private int xTilePos;
    private int yTilePos;
    private Tower towerOnTile;
    private Rock rockOnTile;
    private Grid grid;
    private int checkpoint;

    public Tile(char tile, int xTilePos, int yTilePos){
        this.tile = tile;
        this.xTilePos = xTilePos;
        this.yTilePos = yTilePos;
        this.towerOnTile = null;
        this.rockOnTile = null;
        this.checkpoint = -1;
        switch (tile){
            case 'E' : isEmpty = true; //empty tile
                break;
            case 'B' : isEmpty = false; // blocked tile (no towers may be placed here)
                break;
            case 'T': isEmpty = false; // tower on this tile
                break;
            case 'R': isEmpty = false; // rock on this tile
                break;
            case '0': isEmpty = false; // 0th checkpoint
                checkpoint = 0;
                break;
            case '1': isEmpty = false; // 1st checkpoint
                checkpoint = 1;
                break;
            case '2': isEmpty = false; // 2nd checkpoint
                checkpoint = 2;
                break;
            case '3': isEmpty = false; // 3rd checkpoint
                checkpoint = 3;
                break;
            case '4': isEmpty = false; // 4th checkpoint
                checkpoint = 4;
                break;
            case '5': isEmpty = false; // 5th checkpoint
                checkpoint = 5;
                break;
            case '6': isEmpty = false; // 6th checkpoint
                checkpoint = 6;
                break;
            case '7': isEmpty = false; // 7th checkpoint
                checkpoint = 7;
                break;
            case '8': isEmpty = false; // 8th checkpoint
                checkpoint = 8;
                break;
            case '9': isEmpty = false; // 9th checkpoint
                checkpoint = 9;
                break;
            default: throw new Error("Unknown grid type");
        }
    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public void setHasTower(){
        isEmpty = false;
    }

    public void setHasNoTower(){
        isEmpty = true;
    }

    public void setIsEmpty() { isEmpty = true; }

    public int getCheckpoint(){ return checkpoint; }

    public int getyTilePos() {
        return yTilePos;
    }

    public int getxTilePos() {
        return xTilePos;
    }

    public void setTowerOnTile(Tower towerOnTile) {
        this.towerOnTile = towerOnTile;
        this.rockOnTile = null;
    }

    public void setRockOnTile(Rock rockOnTile) {
        this.rockOnTile = rockOnTile;
        this.towerOnTile = null;
    }

    public void setNoRockOnTile(){
        this.rockOnTile = null;
        this.towerOnTile = null;
    }

    // todo add getting the selected sprite
    public Sprite getSpriteOnTile() {
        if (towerOnTile != null){
            return towerOnTile;
        } else if (rockOnTile != null){
            return rockOnTile;
        } else return null;
    }

    public Rock getRockOnTile() {
        return rockOnTile;
    }

    // two tiles are the same if they have the same coordinates
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (xTilePos != tile.xTilePos) return false;
        return yTilePos == tile.yTilePos;

    }

    @Override
    public int hashCode() {
        int result = xTilePos;
        result = 31 * result + yTilePos;
        return result;
    }
}

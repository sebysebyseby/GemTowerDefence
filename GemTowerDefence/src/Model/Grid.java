package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2015-09-01.
 */
public class Grid {

    private static Grid instance;
    private List<List<Tile>> mapArray;
    private Point[] checkPointList;

    private Grid(String map){ //grid is made from the top left to bottom right, the way a normal page is read
        int numRows = 0;
        List<List<Tile>> rows = new ArrayList<List<Tile>>();
        checkPointList = new Point[10];

        for (int i = 0; i < Constants.NUM_TILES_HIGH; i++){
            List<Tile> row = new ArrayList<>();
            for (int j = 0; j < Constants.NUM_TILES_WIDE; j++){
                Tile tile = new Tile(map.charAt(j + numRows * Constants.NUM_TILES_WIDE), j, i);
                row.add(j, tile);
                if (tile.getCheckpoint() >= 0) {
                    setIthCheckpointListElement(tile.getCheckpoint(), new Point(tile.getxTilePos() * 10, tile.getyTilePos() * 10));
                }
            }
            numRows += 1;
            rows.add(row);
        }
        mapArray = rows;
    }

    public static Grid getInstance(){
        if (instance == null){
            instance = new Grid(Constants.DEFAULT_MAP);
        }
        return instance;
    }

    public List<List<Tile>> getMapArray(){
        return mapArray;
    }

    // Determines if the position given (in real coordinates, not tile coordinates) can something placed on it.
    // Returns the position of where the sprite will be placed (in real coordinates) if the tile that contains the
    // point given is empty. as well as the tiles to its right, bottom, and bottom right are also empty.
    // Returns null if it cannot be placed here.
    public Point locationToPlace(int x, int y){
        int ts = Constants.TILE_SIZE;
        int column = (x / ts);
        int row = (y / ts);
        if (row == Constants.NUM_TILES_WIDE - 1){       // if the position to place something is at the bottom, pretend
            row -= 1;                                   // the place clicked was one higher
        }
        if  (column == Constants.NUM_TILES_HIGH - 1){   // if the position to place something is at the far right, pretend
            column -= 1;                                // the place clicked was one to the left
        }

        if (mapArray.get(row).get(column).isEmpty() &&
                mapArray.get(row + 1).get(column).isEmpty() &&
                mapArray.get(row).get(column + 1).isEmpty() &&
                mapArray.get(row + 1).get(column + 1).isEmpty()){
            return new Point((column * ts), (row * ts));
        }
        else return null;
    }


    // Returns the point corresponding to the top left of the tile clicked. If the tile clicked is on the very bottom,
    // the snap will shift one up. Likewise, if the tile clicked is on the far right, the snap will shift one to the left.
    public static Point snapToGrid(Point point){
        int ts = Constants.TILE_SIZE;
        int column = (int) (point.getX() / ts);
        int row = (int) (point.getY() / ts);
        if (row == Constants.NUM_TILES_WIDE - 1){       // if the position to place something is at the bottom, pretend
            row -= 1;                                   // the place clicked was one higher
        }
        if  (column == Constants.NUM_TILES_HIGH - 1){   // if the position to place something is at the far right, pretend
            column -= 1;                                // the place clicked was one to the left
        }
        return new Point(column * ts, row * ts);
    }

    // when a tower is added, all tiles it occupies are set to blocked and have their towerOnTile field set to the tower given.
    // sets the "hasRock" to null
    public void addTower(Tower t){
        int ts = Constants.TILE_SIZE;
        int column = ((t.getxPos()) / ts);
        int row = ((t.getyPos()) / ts);
        mapArray.get(row).get(column).setHasTower();
        mapArray.get(row).get(column).setTowerOnTile(t);
        mapArray.get(row + 1).get(column).setHasTower();
        mapArray.get(row + 1).get(column).setTowerOnTile(t);
        mapArray.get(row).get(column + 1).setHasTower();
        mapArray.get(row).get(column + 1).setTowerOnTile(t);
        mapArray.get(row + 1).get(column + 1).setHasTower();
        mapArray.get(row + 1).get(column + 1).setTowerOnTile(t);
    }

    // when a tower is added, all tiles it occupies are set to blocked and have their towerOnTile field set to the tower given.
    // sets the "hasTower" to null
    public void addRock(Rock r){
        int ts = Constants.TILE_SIZE;
        int column = ((r.getXpos()) / ts);
        int row = ((r.getYpos()) / ts);
        mapArray.get(row).get(column).setHasTower();
        mapArray.get(row).get(column).setRockOnTile(r);
        mapArray.get(row + 1).get(column).setHasTower();
        mapArray.get(row + 1).get(column).setRockOnTile(r);
        mapArray.get(row).get(column + 1).setHasTower();
        mapArray.get(row).get(column + 1).setRockOnTile(r);
        mapArray.get(row + 1).get(column + 1).setHasTower();
        mapArray.get(row + 1).get(column + 1).setRockOnTile(r);
    }

    public void removeRock(Rock r){
        int ts = Constants.TILE_SIZE;
        int column = ((r.getXpos()) / ts);
        int row = ((r.getYpos()) / ts);
        mapArray.get(row).get(column).setHasNoTower();
        mapArray.get(row).get(column).setNoRockOnTile();
        mapArray.get(row + 1).get(column).setHasNoTower();
        mapArray.get(row + 1).get(column).setNoRockOnTile();
        mapArray.get(row).get(column + 1).setHasNoTower();
        mapArray.get(row).get(column + 1).setNoRockOnTile();
        mapArray.get(row + 1).get(column + 1).setHasNoTower();
        mapArray.get(row + 1).get(column + 1).setNoRockOnTile();
    }


    // Returns the Tile at point p
    public Tile getTileAtPoint(Point p){
        int ts = Constants.TILE_SIZE;
        int column = (int) (p.getX() / ts);
        int row = (int) (p.getY() / ts);
        return mapArray.get(row).get(column);
    }

    // Returns the sprite occuping the tile containing Point p. (ROCK OR TOWER ONLY)
    public Sprite getSpriteAtPoint(Point p){
       return getTileAtPoint(p).getSpriteOnTile();
    }

    // Sets the ith element in the checkpointList to the Point given
    public void setIthCheckpointListElement(int i, Point p){
        checkPointList[i] = p;
    }

    public Point[] getCheckpointList(){
        return checkPointList;
    }


}

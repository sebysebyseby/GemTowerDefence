package UI;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Sebastian on 2015-08-06.
 */
public class GamePanel extends JPanel implements MouseListener {

    private static GamePanel theGamePanel= null;
    private Game game = Game.getInstance();
    private Grid grid = Grid.getInstance();
    private InformationPanel infoPanel = InformationPanel.getTheInformationPanel();
    private boolean isTowerPlacingMode = true;
    private boolean isWaveOver = false;
    private boolean isTowerPlacingModePaused = true;
    private boolean isGameOver = false;

    /**
     * Constructor for the GamePanel (the playable area of the game)
     * */
    private GamePanel(){
        setPreferredSize(new Dimension(UIConstants.GAME_PANEL_WIDTH,UIConstants.GAME_PANEL_HEIGHT)); // preferred size of the playable area
        setBackground(UIConstants.GAME_PANEL_COLOR);

        addMouseListener(this);
    }
    // Getter for the only possible instance of GamePanel
    public static GamePanel getTheGamePanel(){
        if (theGamePanel == null){
            theGamePanel = new GamePanel();
        }
        return theGamePanel;
    }

    public boolean isWaveOver() {
        return isWaveOver;
    }

    // Used for loading the image to place next
    BufferedImage img = null;
    public void loadImage(String s){
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the status of the gamePanel
    public void updateMode(){
        isWaveOver = game.isWaveOver();
        isTowerPlacingMode = game.isTowerPlacingMode();
        isTowerPlacingModePaused = game.isTowerPlacingModePaused();
        isGameOver = game.getIsGameOver();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        if (!isWaveOver) {
//            game.updateGame();
//        }
        drawGame(g);

    }

    // draws the game
    public void drawGame(Graphics g){
        drawBackground(g);
        drawTowers(g);
        drawRocks(g);
        drawCursor(g);
        drawNewTowers(g);
        drawEnemies(g);
        drawSelected(g);
        drawLines(g);
        drawEnemyHealth(g);
        drawGameOverScreen(g);
    }

    //draws the background of the game
    public void drawBackground(Graphics g){
        loadImage("Images/Background.png");
        g.drawImage(img, 0, 0, this);
    }

    //draws the cursor, snapped to the grid
    public void drawCursor(Graphics g){
        if (isTowerPlacingMode && !isTowerPlacingModePaused){
            Point screenPoint = MouseInfo.getPointerInfo().getLocation();
            Point windowLocation = getLocationOnScreen();
            Point p = new Point((int) (screenPoint.getX() - windowLocation.getX()),
                    (int) (screenPoint.getY() - windowLocation.getY()));
            p = Grid.snapToGrid(p);
            loadImage("Images/Rock.png");
            g.drawImage(img, (int) p.getX(), (int) p.getY(), this);
        }
    }

    // draws all towers in the game
    public void drawTowers(Graphics g){
        for (Tower t: game.getTowers()){
            loadImage(t.getURL());
            g.drawImage(img, t.getxPos(), t.getyPos(), this);
        }
    }

    public void drawRocks(Graphics g){
        for (Rock r: game.getRocks()){
            loadImage("Images/Rock.png");
            g.drawImage(img, r.getXpos(), r.getYpos(), this);
        }
    }

    // draws the possible choices for a new tower
    public void drawNewTowers(Graphics g){
        for (Tower t: game.getLast5Built()){
            loadImage(t.getURL());
            g.drawImage(img, t.getxPos(), t.getyPos(), this);
            loadImage("Images/Last5.png");
            g.drawImage(img, t.getxPos(), t.getyPos(), this);
        }

    }

    // draws the "selected" border around the selected tile if it has a tower or a rock,
    // draws a circle indicating range around the selected tile, if it has a tower
    // draws the "EnemySelected" border on top of the selected enemy
    public void drawSelected(Graphics g){
        if (game.getSelected() != null){
            int fullRange;
            int offset;
            switch (game.getSelected().getClassNumber()){ // grid.getSpriteAtPoint(p).getClassNumber()
                case 1: // Gem case
                    Gem gem = (Gem) game.getSelected(); // grid.getSpriteAtPoint(p);
                    loadImage("Images/Selected.png");
                    g.drawImage(img, gem.getxPos(), gem.getyPos(), this);
                    fullRange = (int) (gem.getRange() * 2);
                    offset = (int) ((gem.getRange()) - 10);
                    g.drawOval(gem.getxPos() - offset, gem.getyPos() - offset, fullRange, fullRange);
                    break;
                case 2: // Special tower case
                    SpecialTower st = (SpecialTower) game.getSelected();
                    loadImage("Images/Selected.png");
                    g.drawImage(img, st.getxPos(), st.getyPos(), this);
                    fullRange = (int) st.getRange();
                    offset = (int) ((st.getRange()) -10);
                    g.drawOval(st.getxPos() - offset, st.getyPos() - offset, fullRange, fullRange);
                    break;
                case 3: // Enemy case
                    Enemy e = (Enemy) game.getSelected();
                    loadImage("Images/EnemySelected.png");
                    g.drawImage(img, e.getxPos(), e.getyPos(), this);
                    break;
                case 4: // Rock case
                    Rock r = (Rock) game.getSelected();
                    loadImage("Images/Selected.png");
                    g.drawImage(img, r.getXpos(), r.getYpos(), this);
                    break;
            }
//                g.drawOval((int) p.getX(), (int) p.getY(), (int) grid.getSpriteAtPoint(p).getRange(), (int) grid.getSpriteAtPoint(p).getRange());         }
        }
    }

    public void drawEnemies(Graphics g){
        if (!isWaveOver){
             for (Enemy e: game.getEnemies()) {
                 loadImage("Images/Enemy.png");
                 g.drawImage(img, e.getxPos(), e.getyPos(), this);
             }
        }
    }

    public void drawLines(Graphics g){
        for (Line line : game.getLines()) {
            line.draw(g);
        }
        Iterator<Line> li = game.getLines().iterator();
        while (li.hasNext()){
            Line line = li.next();
            if (line.getAlpha() <= 0){
                li.remove();
            }
        }
    }

    public void drawGameOverScreen(Graphics g){
        if (isGameOver){
            loadImage("Images/GameOver.png");
            g.drawImage(img, 0, 0, this);
        }
    }


    int test;
    public void test(Enemy e) {
        test = (int) (e.getCurrentHP() * 20 ) / e.getMaxHP();
    }

    // draws the health bar for each enemy by drawing a solid red rectangle and a green rectangle on top of that
    // the green rectangle is determined by the fraction of health it has, times 10 (the rectangles are maximum width 20
    public void drawEnemyHealth(Graphics g){
        if (!isWaveOver){
            for (Enemy e: game.getEnemies()){
                Color save = g.getColor();
                g.setColor(new Color(200, 31, 8));
                g.fillRect(e.getxPos(), e.getyPos(), 20, 3);
                g.setColor(save);

                save = g.getColor();
                g.setColor(new Color(131, 255, 88));
                test(e);
                g.fillRect(e.getxPos(), e.getyPos(), test , 3);
                g.setColor(save);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    // When the mouse is pressed on the GamePanel...
    // if it is
    @Override
    public void mousePressed(MouseEvent e) {
        if (isTowerPlacingMode && !isTowerPlacingModePaused){ // if the tower can be placed and there are less than 5 towers queued, add a tower to
            Point location = grid.locationToPlace(e.getX(), e.getY());      // the last 5 built
            if (location != null && game.getLast5Built().size() < 5){
                Gem g = game.newRandomGem();
                System.out.println(g.getGrade() + " " + g.getType());
                Gem gem = new Gem(g.getType(), g.getGrade(), g.getxPos(), g.getyPos(),
                        g.getLowDamage(), g.getHighDamage(), g.getRange(), g.getMaxCooldown());
                gem.setxPos((int) location.getX());
                gem.setyPos((int) location.getY());
                game.getLast5Built().add(gem);
                grid.addTower(gem);
                if (game.getLast5Built().size() >= 5){
                    game.setIsTowerPlacingMode(false);
                }
                game.setSelected(gem);
                //selectedTile = gem.getPoint();
            }
            else {
                System.out.println("cannot place tower here");
                game.setIsTowerPlacingModePaused(true);
            }
        }
        else if (isTowerPlacingMode && isTowerPlacingModePaused){
            Point selectedTile;
            selectedTile = Grid.snapToGrid(e.getPoint()); //selected tile = the tile with the point clicked
            game.setSelected(grid.getSpriteAtPoint(selectedTile)); // sets the game's "selected" to the tower clicked
        }
        else if (!isTowerPlacingMode) {
            Point selectedTile;
            selectedTile = Grid.snapToGrid(e.getPoint()); //selected tile = the tile with the point clicked
            game.setSelected(grid.getSpriteAtPoint(selectedTile)); // sets the game's "selected" to the tower clicked
            if (game.getSelected() == null){
                game.setSelectedToEnemyNearPoint(e.getPoint());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

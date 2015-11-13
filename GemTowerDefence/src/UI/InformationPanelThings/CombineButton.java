package UI.InformationPanelThings;

import Model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-10.
 */
public class CombineButton extends Button implements MouseListener{

    private Game game;
    private Grid grid;

    private static CombineButton instance;

    private CombineButton(){
        super("Combine");
        game = Game.getInstance();
        grid = Grid.getInstance();
        addMouseListener(this);
//        setUsability(false); // for some reason the game hates having this uncommented
    }

    public static CombineButton getInstance(){
        if (instance == null){
            instance = new CombineButton();
        }
        return instance;
    }

    public void setUsability(boolean b){
        setEnabled(b);
        setVisible(b);
    }


    // adds the selected gem from the last5picked into the list of towers in the game, but "upgrades" the gem grade
    // before doing so
    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getSelected() != null){
            for (Gem gem: game.getLast5Built()){
                if (!gem.equals(game.getSelected())){
                    Rock rock = new Rock(gem.getxPos(), gem.getyPos());
                    game.getRocks().add(rock);
                    grid.addRock(rock);
                }
                else {
                    String type = gem.getType();
                    String grade = gem.getGrade();
                    int xPos = gem.getxPos();
                    int yPos = gem.getyPos();
                    switch (gem.getGrade()){
                        case "Chipped": grade = ("Flawed");
                            break;
                        case "Flawed": grade =("Normal");
                            break;
                        case "Normal": grade =("Flawless");
                            break;
                        case "Flawless": grade = ("Perfect");
                            break;
                        case "Perfect": grade = ("Great");
                            break;
                    }

                    for (Gem g: Constants.allPossibleGems){
                        if (g.getType().equals(type) && g.getGrade().equals(grade)){
                            Gem newGem = new Gem(type, grade, xPos, yPos, g.getLowDamage(), g.getHighDamage(), g.getRange(), g.getMaxCooldown());
                            game.getTowers().add(newGem);
                            grid.addTower(newGem);

                            game.setSelected(grid.getSpriteAtPoint(new Point(xPos, yPos)));
                        }
                    }
                }
            }
            game.getLast5Built().clear();
        }
        game.setIsWaveOver(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

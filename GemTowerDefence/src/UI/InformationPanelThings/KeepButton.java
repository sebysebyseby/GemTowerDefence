package UI.InformationPanelThings;

import Model.Game;
import Model.Gem;
import Model.Grid;
import Model.Rock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-10.
 */
public class KeepButton extends Button implements MouseListener{

    private static KeepButton instance;
    private Game game;
    private Grid grid;

    private KeepButton(){
        super("Keep");
        game = Game.getInstance();
        grid = Grid.getInstance();
        addMouseListener(this);
//        setUsability(false); // for some reason the game hates having this uncommented
    }

    public static KeepButton getInstance(){
        if (instance == null){
            instance = new KeepButton();
        }
        return instance;
    }

    public void setUsability(boolean b){
        setEnabled(b);
        setVisible(b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // the keep button is pressed, keep the tower selected and turn the rest to rocks, the isWaveOver set to false
    // the keep button is assumed to appear only if a valid selection has been made
    @Override
    public void mousePressed(MouseEvent e) {
        if (game.getSelected() != null && game.getLast5Built().contains(game.getSelected())){
            for (Gem gem: game.getLast5Built()) {
                if (!gem.equals(game.getSelected())){
                    Rock rock = new Rock(gem.getxPos(), gem.getyPos());
                    game.getRocks().add(rock);
                    grid.addRock(rock);
                }
                else {
                    game.getTowers().add(gem);
                    grid.addTower(gem);
                }
            }
            game.getLast5Built().clear();
        }
        game.setIsWaveOver(false);
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

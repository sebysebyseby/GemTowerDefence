package UI.InformationPanelThings;

import Model.Game;
import Model.Grid;
import Model.Rock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-10.
 */
public class PlaceGemsButton extends Button implements MouseListener{

    private static PlaceGemsButton instance;
    private Game game;

    private PlaceGemsButton(){
        super("Place Gems");
        game = Game.getInstance();
        addMouseListener(this);
//        setUsability(false); // for some reason the game hates having this uncommented
    }

    public static PlaceGemsButton getInstance(){
        if (instance == null){
            instance = new PlaceGemsButton();
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

    // Resumes placing gems
    @Override
    public void mousePressed(MouseEvent e) {
        game.setIsTowerPlacingModePaused(false);
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

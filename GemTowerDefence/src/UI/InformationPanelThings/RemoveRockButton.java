package UI.InformationPanelThings;

import Model.Game;
import Model.Grid;
import Model.Rock;
import UI.InformationPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-10.
 */
public class RemoveRockButton extends Button implements MouseListener{

    private static RemoveRockButton instance;
    private Game game;
    private Grid grid;

    private RemoveRockButton(){
        super("Remove");
        game = Game.getInstance();
        grid = Grid.getInstance();
        addMouseListener(this);
//        setUsability(false); // for some reason the game hates having this uncommented
    }

    public static RemoveRockButton getInstance(){
        if (instance == null){
            instance = new RemoveRockButton();
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

    // Assuming that a rock is selected, removes the selected rock from the game
    @Override
    public void mousePressed(MouseEvent e) {
        if (game.getSelected().getClassNumber() != 4){
            throw new Error("Tried removing rock, but no rock was selected");
        }
        if (game.getSelected() != null){
            game.getRocks().remove(game.getSelected());
            grid.removeRock((Rock) game.getSelected());
            game.setSelected(null);
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

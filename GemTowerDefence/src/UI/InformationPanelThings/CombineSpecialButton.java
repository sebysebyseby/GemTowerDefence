package UI.InformationPanelThings;

import Model.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-10.
 */
public class CombineSpecialButton extends Button implements MouseListener{

    private static CombineSpecialButton instance;
    private Game game = Game.getInstance();

    private CombineSpecialButton(){
        super("Combine Special");
        game = Game.getInstance();
        addMouseListener(this);
    }

    public static CombineSpecialButton getInstance(){
        if (instance == null){
            instance = new CombineSpecialButton();
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

package UI.TopPanelThings;

import Model.Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sebastian on 2015-09-30.
 */
public class BuyLivesButton extends Button implements MouseListener{

    int amount;
    static final int LIVES_COST = 10;
    Game game;

    public BuyLivesButton(int i){
        this.amount = i;
        setLabel("Buy " + amount + " lives: " + amount*LIVES_COST + " g");
        this.game = Game.getInstance();
        addMouseListener(this);
    }


    // buy "amount" of lives if they can be afforded
    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getGold() >= amount * LIVES_COST){
            game.setGold(game.getGold() - amount *LIVES_COST);
            game.setLives(game.getLives() + amount);
        }
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

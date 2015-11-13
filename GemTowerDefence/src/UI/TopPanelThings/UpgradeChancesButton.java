package UI.TopPanelThings;

import Model.Constants;
import Model.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by Sebastian on 2015-09-30.
 */
public class UpgradeChancesButton extends Button implements MouseListener {

    int cost;
    int upgradesBought;
    List<String> chances = Constants.CHANCES;
    Game game = Game.getInstance();


    public UpgradeChancesButton(){
        setLabel(chances.get(0) + "g");
        this.upgradesBought = 0;
        this.cost = getCost(chances.get(upgradesBought));
        addMouseListener(this);
    }

    // extract the integer for the cost of upgrading chances by taking the rest of the string after the ":"
    public int getCost(String s){
        for (int i = 0; i<s.length(); i++){
            if (s.charAt(i) == ':'){
                return Integer.parseInt(s.substring(i+1).trim());
            }
        }
        throw new Error("Could not find \":\" in the upgrade chances string");
    }

    // accepts strings in the format "#/#/#/#/# : #", sets the chances of gems to the appropriate new chance
    public void setNewChances(String s){
        int startOfInt = 0;
        int chance = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '/' || s.charAt(i) == ':') {
                switch (chance){
                    case 0: game.setChippedChance(Integer.parseInt(s.substring(startOfInt, i).trim()));
                        break;
                    case 1: game.setFlawedChance(Integer.parseInt(s.substring(startOfInt, i).trim()));
                        break;
                    case 2: game.setNormalChance(Integer.parseInt(s.substring(startOfInt, i).trim()));
                        break;
                    case 3: game.setFlawlessChance(Integer.parseInt(s.substring(startOfInt, i).trim()));
                        break;
                    case 4: game.setPerfectChance(Integer.parseInt(s.substring(startOfInt, i).trim()));
                        break;
                    default: throw new Error("Could not read chances information ccorrectly");
                }
                startOfInt = i + 1;
                chance += 1;
            }
        }
    }


    // if there is enough money to buy the next upgrade chances cost, sets the game's new chances,
    // increases the number of upgrades bought, and the gold in the game is decreased by the upgrade cost
    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getGold() >= getCost(chances.get(upgradesBought))){
            setNewChances(chances.get(upgradesBought));
            game.setGold(game.getGold() - cost);
            upgradesBought += 1;
            cost = getCost(chances.get(upgradesBought));
            setLabel(chances.get(upgradesBought));
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

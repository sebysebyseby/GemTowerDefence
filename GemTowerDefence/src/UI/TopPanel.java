package UI;

import Model.Game;
import UI.TopPanelThings.BuyLivesButton;
import UI.TopPanelThings.UpgradeChancesButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian on 2015-08-06.
 */
public class TopPanel extends JPanel {

    private static TopPanel theTopPanel = null;
    private Game game;
    private JLabel level;
    private JLabel lives;
    private JLabel gold;
    private JLabel chances;
    private BuyLivesButton buy1LivesButton;
    private BuyLivesButton buy10LivesButton;
    private BuyLivesButton buy100LivesButton;
    private UpgradeChancesButton upgradeChancesButton;
    /*
    * Constructor
    */
    private TopPanel(){
        setPreferredSize(new Dimension(UIConstants.TOP_PANEL_WIDTH,UIConstants.TOP_PANEL_HEIGHT));
        setBackground(UIConstants.TOP_PANEL_COLOR);
        game = Game.getInstance();
        level = new JLabel("Level: 1");
        level.setPreferredSize(new Dimension(55, 20));
        lives = new JLabel("Lives: 10");
        lives.setPreferredSize(new Dimension(60, 20));
        gold = new JLabel("Gold: 10");
        gold.setPreferredSize(new Dimension(70, 20));
        chances = new JLabel("Chances: 100/0/0/0/0");
        chances.setPreferredSize(new Dimension(150, 20));
        add(level);
        add(lives);
        add(gold);
        add(chances);
        buy1LivesButton = new BuyLivesButton(1);
        buy1LivesButton.setPreferredSize(new Dimension(110,20));
        buy10LivesButton = new BuyLivesButton(10);
        buy10LivesButton.setPreferredSize(new Dimension(110, 20));
        buy100LivesButton = new BuyLivesButton(100);
        buy100LivesButton.setPreferredSize(new Dimension(110, 20));
        upgradeChancesButton = new UpgradeChancesButton();
        upgradeChancesButton.setPreferredSize(new Dimension(150, 20));
        add(buy1LivesButton);
        add(buy10LivesButton);
//        add(buy100LivesButton);
        add(upgradeChancesButton);
    }

    public static TopPanel getTheTopPanel(){
        if (theTopPanel == null){
            theTopPanel = new TopPanel();
        }
        return theTopPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        updateTopPanel();
        super.paintComponent(g);
    }

    public void updateTopPanel(){
        level.setText("Level: " + game.getLevel());
        lives.setText("Lives: " + game.getLives());
        gold.setText("Gold: " + game.getGold());
        chances.setText("Chances: " + game.getChippedChance() + "/" + game.getFlawedChance() + "/"
        + game.getNormalChance() + "/" + game.getFlawlessChance() + "/" + game.getPerfectChance());
    }


}

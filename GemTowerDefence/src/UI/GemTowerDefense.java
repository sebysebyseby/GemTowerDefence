package UI;

import Model.Constants;
import Model.Game;
import Model.Gem;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sebastian on 2015-08-05.
 */
public class GemTowerDefense extends JFrame {
    private static final int INTERVAL_SLOW = 40;
    private static final int INTERVAL_NORMAL = 30;
    private static final int INTERVAL_QUICK = 15;
    private static final int INTERVAL_FAST = 10;
    private Game game = Game.getInstance();
    private GamePanel gamePanel = GamePanel.getTheGamePanel();
    private TopPanel topPanel = TopPanel.getTheTopPanel();
    private InformationPanel informationPanel = InformationPanel.getTheInformationPanel();
    private Timer uiTimer;
    private Timer gameTimer;

    /**
     * Constructor for GemTowerDefense game; sets up the main window
     */
    public GemTowerDefense(){
        JFrame frame = new JFrame("Gem Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(GamePanel.getTheGamePanel(), BorderLayout.LINE_START);
        frame.add(InformationPanel.getTheInformationPanel(), BorderLayout.LINE_END);
        frame.add(TopPanel.getTheTopPanel(),BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startRenderTimer();
        startGameTicker();


    }


    public static void main(String[] args) {
        new GemTowerDefense();
    }

    // updates what happens in the game 120 times/sec (move enemies, etc)
    public void startGameTicker() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // what actually gets done every tick
                if (!gamePanel.isWaveOver()) {
                    game.updateGame();
                }
            }
        });
        gameTimer.start();
        for (Gem g: Constants.allPossibleGems){
            g.retrieveUpgradeDetails();
        }

    }

    public void startRenderTimer(){
        if (uiTimer != null){
            uiTimer.stop();
        }
        uiTimer = new Timer(1000/60, new ActionListener() { // the interval between ticks in milliseconds (60fps at the moment)
            @Override
            public void actionPerformed(ActionEvent e) {  // what actually gets done every tick
                informationPanel.repaint();
                gamePanel.updateMode();
                gamePanel.repaint();
                topPanel.repaint();
            }
        });
        uiTimer.start();
    }

}

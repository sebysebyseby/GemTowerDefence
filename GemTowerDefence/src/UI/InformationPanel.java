package UI;

import Model.*;
import UI.InformationPanelThings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by Sebastian on 2015-08-06.
 */
public class InformationPanel extends JPanel implements MouseListener {

    private static InformationPanel theInformationPanel = null;
    private GamePanel gamePanel;
    private Game game = Game.getInstance();
    private Grid grid = Grid.getInstance();
    private KeepButton keepButton;
    private CombineSpecialButton combineSpecialButton;
    private CombineButton combineButton;
    private PlaceGemsButton placeGemsButton;
    private RemoveRockButton removeRockButton;
    private JLabel name;
    private JLabel type;
    private JLabel damage;
    private JLabel kills;
    private JLabel level;
    private JLabel range;
    private JLabel cooldown;


    /*
    * Constructor
    */
    private InformationPanel() {
        setPreferredSize(new Dimension(UIConstants.INFORMATION_PANEL_WIDTH, UIConstants.INFORMATION_PANEL_HEIGHT));
        setBackground(UIConstants.INFORMATION_PANEL_COLOR);

        name = new JLabel("Name: ");
        name.setPreferredSize(new Dimension(180, 15));
        type = new JLabel("Type: ");
        type.setPreferredSize(new Dimension(180, 15));
        damage = new JLabel("Damage: ");
        damage.setPreferredSize(new Dimension(180, 15));
        kills = new JLabel("Kills: ");
        kills.setPreferredSize(new Dimension(90, 15));
        level = new JLabel("Level: ");
        level.setPreferredSize(new Dimension(90, 15));
        range = new JLabel("Range: ");
        range.setPreferredSize(new Dimension(180, 15));
        cooldown = new JLabel("Cooldown: ");
        cooldown.setPreferredSize(new Dimension(180, 15));
        add(name);
        add(type);
        add(damage);
        add(kills);
        add(level);
        add(range);
        add(cooldown);

        keepButton = KeepButton.getInstance();
        combineButton = CombineButton.getInstance();
        combineSpecialButton = CombineSpecialButton.getInstance();
        placeGemsButton = PlaceGemsButton.getInstance();
        removeRockButton = RemoveRockButton.getInstance();
        add(keepButton, BorderLayout.SOUTH);
        add(combineButton, BorderLayout.NORTH);
        add(combineSpecialButton);
        add(placeGemsButton);
        add(removeRockButton);

        addMouseListener(this);
    }

    public static InformationPanel getTheInformationPanel(){
        if (theInformationPanel == null){
            theInformationPanel = new InformationPanel();
        }
        return theInformationPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        updateInfo();
        super.paintComponent(g);
    }

    // updates the usability of the buttons in the information panel;
    // if the wave is not in progress, and towers are not being placed sets the "keep" button to usable, otherwise set it to unusable
    // if the wave is not in progress. towers are not being placed, and there are 2 or more of the same gem in the list of last5built,
    //      then the "combine button" is set to useable.
    public void updateInfo(){
        if (!game.isTowerPlacingMode() && game.isWaveOver() && game.getSelected() != null){
            // keep
            if (game.getLast5Built().contains(game.getSelected())) {
                keepButton.setUsability(true);

                // combine
                int i = 0;
                Gem g = (Gem) game.getSelected();
                for (Gem gem: game.getLast5Built()){
                    if (g.getGrade().equals(gem.getGrade()) && g.getType().equals(gem.getType())) i++;
                }
                if (i >= 2) {
                    combineButton.setUsability(true);
                } else combineButton.setUsability(false);

                // combine special
                if (game.getSelected().getClassNumber() == 1){
                    Gem selected = (Gem) game.getSelected();
                    List<Gem> otherGems = ((Gem) selected).getOtherGemsToCombineWith();
                }

                // todo combineSpecialButton Condition
            }
        } else {
            keepButton.setUsability(false);
            combineButton.setUsability(false);
            combineSpecialButton.setUsability(false);
        }
        // if a rock is selected, adds the option to remove the rock
        if (game.isTowerPlacingMode() && game.getSelected() != null && game.getSelected().getClassNumber() == 4){
            removeRockButton.setUsability(true);
        } else removeRockButton.setUsability(false);

        // if towers are being placed but the process is paused, add the option to start adding gems again
        if (game.isTowerPlacingMode() && game.isTowerPlacingModePaused()){
            placeGemsButton.setUsability(true);
        } else placeGemsButton.setUsability(false);

        // updates the labels if something is selected and towers aren't being placed
        if (game.getSelected() != null) {
//        if (!game.isTowerPlacingMode() && game.getSelected() != null){
            updateLabels();
        } else setLabelVisibility(false);
    }

    // updates the label information based on what is selected, and set the visibility of those labels accordingly
    public void updateLabels(){
        switch (game.getSelected().getClassNumber()){
            case 1: Gem g = (Gem) game.getSelected(); //gem selected
                name.setText(g.getGrade() + " " + g.getType());
                type.setText("Type: " + g.getType());
                kills.setText("Kills: " + g.getKills());
                level.setText("Level: " + g.getLevel());
                damage.setText("Damage: " + g.getLowDamage() + " - " + g.getHighDamage());
                range.setText("Range: " + g.getRange());
                cooldown.setText("Cooldown: " + g.getMaxCooldown());
                setLabelVisibility(true);
                break;
            case 2: SpecialTower s = (SpecialTower) game.getSelected(); //special tower selected
                name.setText(s.getName());
                type.setText("Type: " + s.getType());
                kills.setText("Kills: " + s.getKills());
                level.setText("Level: " + s.getLevel());
                damage.setText("Damage: " + s.getLowDamage() + " - " + s.getHighDamage());
                range.setText("Range: " + s.getRange());
                cooldown.setText("Cooldown: " + s.getMaxCooldown());
                break;
            case 3: Enemy e = (Enemy) game.getSelected();//todo: an enemy gets selected
                name.setText("Enemy !");
                type.setText("Topaz Weak");
                kills.setText("Lives Cost: " + e.getLivesCost());
                level.setText("HP: " + e.getCurrentHP() + " / " + e.getMaxHP());
                setLabelVisibility(true);
                break;
            case 4: Rock r = (Rock) game.getSelected();
                setLabelVisibility(false);

        }
    }

    public void setLabelVisibility(boolean b){
        name.setVisible(b);
        type.setVisible(b);
        kills.setVisible(b);
        level.setVisible(b);
        damage.setVisible(b);
        range.setVisible(b);
        cooldown.setVisible(b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // if when picking gems the info panel is clicked, the placing mode gets paused
    @Override
    public void mousePressed(MouseEvent e) {
        if (game.isTowerPlacingMode() && !game.isTowerPlacingModePaused()){
            game.setIsTowerPlacingModePaused(true);
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

package UI;

import Model.Constants;
import Model.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian on 2015-08-30.
 */
public class UIConstants {
    static final int GAME_PANEL_WIDTH = Constants.NUM_TILES_WIDE * Constants.TILE_SIZE;
    static final int INFORMATION_PANEL_WIDTH = 400;
    static final int TOP_PANEL_WIDTH = GAME_PANEL_WIDTH + INFORMATION_PANEL_WIDTH;

    static final int TOP_PANEL_HEIGHT = 50;
    static final int GAME_PANEL_HEIGHT = Constants.NUM_TILES_HIGH * Constants.TILE_SIZE;
    static final int INFORMATION_PANEL_HEIGHT = GAME_PANEL_HEIGHT;

    static final Color GAME_PANEL_COLOR = new Color(227, 244, 235);
    static final Color INFORMATION_PANEL_COLOR = new Color(108, 108, 108);
    static final Color TOP_PANEL_COLOR = new Color(108, 108, 108);


    /*
    *   ////// Top Panel ////////
    *
    *   ////////////////  ///////
    *   ////////////////  //Info/
    *   // Game Panel //  /Panel/
    *   ////////////////  ///////
    *   ////////////////  ///////
    */

    final static ImageIcon CE  = new ImageIcon("Images/ChippedEmerald.png");
    final static ImageIcon FE  = new ImageIcon("Images/FlawedEmerald.png");
    final static ImageIcon NE  = new ImageIcon("Images/NormalEmerald.png");
    final static ImageIcon FlE = new ImageIcon("Images/FlawlessEmerald.png");
    final static ImageIcon PE  = new ImageIcon("Images/PerfectEmerald.png");

    final static ImageIcon CR  = new ImageIcon("Images/ChippedRuby.png");
    final static ImageIcon FR  = new ImageIcon("Images/FlawedRuby.png");
    final static ImageIcon NR  = new ImageIcon("Images/NormalRuby.png");
    final static ImageIcon FlR = new ImageIcon("Images/FlawlessRuby.png");
    final static ImageIcon PR  = new ImageIcon("Images/PerfectRuby.png");

    final static ImageIcon CS  = new ImageIcon("Images/ChippedSapphire.png");
    final static ImageIcon FS  = new ImageIcon("Images/FlawedSapphire.png");
    final static ImageIcon NS  = new ImageIcon("Images/NormalSapphire.png");
    final static ImageIcon FlS = new ImageIcon("Images/FlawlessSapphire.png");
    final static ImageIcon PS  = new ImageIcon("Images/PerfectSapphire.png");

}

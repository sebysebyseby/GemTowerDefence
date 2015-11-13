package Model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by Sebastian on 2015-08-30.
 */
public class Constants {

    public final static int numOfGemTypes = 8; //make sure to update this if you want all gem types to be able to be made :)
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CE   = new Gem("Emerald"   , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Emerald
    public final static Gem FE   = new Gem("Emerald"   , "Flawed"   , 999, 999,      8,      12,    70, 20); // Flawed   Emerald
    public final static Gem NE   = new Gem("Emerald"   , "Normal"   , 999, 999,      8,      12,   500, 60); // Normal   Emerald
    public final static Gem FlE  = new Gem("Emerald"   , "Flawless" , 999, 999,      8,      12,   500, 60); // Flawless Emerald
    public final static Gem PE   = new Gem("Emerald"   , "Perfect"  , 999, 999,      8,      12,   500, 60); // Perfect  Emerald
    public final static Gem GE   = new Gem("Emerald"   , "Great"    , 999, 999,      8,      12,   500, 60); // Great    Emerald
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CR   = new Gem("Ruby"      , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Ruby
    public final static Gem FR   = new Gem("Ruby"      , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Ruby
    public final static Gem NR   = new Gem("Ruby"      , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Ruby
    public final static Gem FlR  = new Gem("Ruby"      , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Ruby
    public final static Gem PR   = new Gem("Ruby"      , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Ruby
    public final static Gem GR   = new Gem("Ruby"      , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Ruby
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CS   = new Gem("Sapphire"  , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Sapphire
    public final static Gem FS   = new Gem("Sapphire"  , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Sapphire
    public final static Gem NS   = new Gem("Sapphire"  , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Sapphire
    public final static Gem FlS  = new Gem("Sapphire"  , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Sapphire
    public final static Gem PS   = new Gem("Sapphire"  , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Sapphire
    public final static Gem GS   = new Gem("Sapphire"  , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Sapphire
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CT   = new Gem("Topaz"     , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Topaz
    public final static Gem FT   = new Gem("Topaz"     , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Topaz
    public final static Gem NT   = new Gem("Topaz"     , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Topaz
    public final static Gem FlT  = new Gem("Topaz"     , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Topaz
    public final static Gem PT   = new Gem("Topaz"     , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Topaz
    public final static Gem GT   = new Gem("Topaz"     , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Topaz
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CD   = new Gem("Diamond"   , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Diamond
    public final static Gem FD   = new Gem("Diamond"   , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Diamond
    public final static Gem ND   = new Gem("Diamond"   , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Diamond
    public final static Gem FlD  = new Gem("Diamond"   , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Diamond
    public final static Gem PD   = new Gem("Diamond"   , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Diamond
    public final static Gem GD   = new Gem("Diamond"   , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Diamond
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CAq  = new Gem("Aquamarine", "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Aquamarine
    public final static Gem FAq  = new Gem("Aquamarine", "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Aquamarine
    public final static Gem NAq  = new Gem("Aquamarine", "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Aquamarine
    public final static Gem FlAq = new Gem("Aquamarine", "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Aquamarine
    public final static Gem PAq  = new Gem("Aquamarine", "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Aquamarine
    public final static Gem GAq  = new Gem("Aquamarine", "Great"    , 999, 999,      8,      12,   800, 60); // Great    Aquamarine
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CO   = new Gem("Opal"      , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Opal
    public final static Gem FO   = new Gem("Opal"      , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Opal
    public final static Gem NO   = new Gem("Opal"      , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Opal
    public final static Gem FlO  = new Gem("Opal"      , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Opal
    public final static Gem PO   = new Gem("Opal"      , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Opal
    public final static Gem GO   = new Gem("Opal"      , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Opal
    //                                         Gem Type,   Gem Grade,  x/y pos, lowDmg, highDmg, Range, Cooldown.
    public final static Gem CAm  = new Gem("Amethyst"  , "Chipped"  , 999, 999,      2,       4,    30, 20); // Chipped  Amethyst
    public final static Gem FAm  = new Gem("Amethyst"  , "Flawed"   , 999, 999,      8,      12,    70, 60); // Flawed   Amethyst
    public final static Gem NAm  = new Gem("Amethyst"  , "Normal"   , 999, 999,      8,      12,   800, 60); // Normal   Amethyst
    public final static Gem FlAm = new Gem("Amethyst"  , "Flawless" , 999, 999,      8,      12,   800, 60); // Flawless Amethyst
    public final static Gem PAm  = new Gem("Amethyst"  , "Perfect"  , 999, 999,      8,      12,   800, 60); // Perfect  Amethyst
    public final static Gem GAm  = new Gem("Amethyst"  , "Great"    , 999, 999,      8,      12,   800, 60); // Great    Amethyst

    public final static List<Gem> allPossibleGems = new ArrayList<>(Arrays.asList(
            CE,  FE,  NE,  FlE,  PE,  GE,
            CR,  FR,  NR,  FlR,  PR,  GR,
            CS,  FS,  NS,  FlS,  PS,  GS,
            CT,  FT,  NT,  FlT,  PT,  GT,
            CD,  FD,  ND,  FlD,  PD,  GD,
            CAq, FAq, NAq, FlAq, PAq, GAq,
            CO,  FO,  NO,  FlO,  PO,  GO,
            CAm, FAm, NAm, FlAm, PAm, GAm)
    );

    public static int getEnemyMaxHP(int difficulty, int level){
        switch (difficulty){
            case 1: return level * 5 -3;
            case 2: return level * 6;
            case 3: return level * 7;
            case 4: return level * 8;
            case 5: return level * 9;
            default: return 10000000;
        }
    }

    public static int getLivesCost(Game game){
        int level = game.getLevel();
        switch (game.getDifficulty()){
            case 1: return level;
            case 2: return level;
            case 3: return level;
            case 4: return level;
            case 5: return level;
            default: return 10000000;
        }
    }

    public static int getMaxSpeed(Game game){
        int level = game.getLevel();
        switch (game.getDifficulty()){
            case 1: return 50;
            case 2: return 50;
            case 3: return 50;
            case 4: return 50;
            case 5: return 50;
            default: return 50;
        }
    }

    public static final int NUM_TILES_WIDE = 20;
    public static final int NUM_TILES_HIGH = 20;
    public static final int TILE_SIZE = 10;

    // NOTE: WHEN MAKING THESE MAPS: THERE CAN BE AT MOST 10 CHECKPOINTS, INCLUDING THE FINAL EDGE SPOT;; (0 - 9)

    public static final String DEFAULT_MAP =
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "0BBB1BEEEEEEEEEEEEEE" +
                    "BBBBBBEEEEEEEEEEEEEE" +
                    "EEEEBBEEEEEEEEEEEEEE" +
                    "EEEEBBEEEEEEEE4BBBB5" +
                    "EEEEBBEEEEEEEEBBBBBB" +
                    "EEEEBBEEEEEEEEBBEEEE" +
                    "EEEEBBEEEEEEEEBBEEEE" +
                    "EEEEBBEEEEEEEEBBEEEE" +
                    "EEEE2BBBBBBBBB3BEEEE" +
                    "EEEEBBBBBBBBBBBBEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE" +
                    "EEEEEEEEEEEEEEEEEEEE";


    // the cost to upgrade to a particular set of chances:

    // USE THE FOLLOWING FORMAT, (remove brackets and fill in): "(##)/(##)/(##)/(##)/(##) : (cost)". Ensure chances ADD UP TO 100!!
    public static final List<String> CHANCES = new ArrayList<>(Arrays.asList(
            "70/30/0/0/0 : 20",
            "50/30/20/0/0 : 70",
            "30/30/40/0/0 : 110" ,
            "20/30/40/10/0 : 150",
            "10/30/30/30/0 : 190",
            "0/30/30/30/10 : 230"
    ));
}

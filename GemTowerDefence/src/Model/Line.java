package Model;

import java.awt.*;

/**
 * Created by Sebastian on 2015-10-14.
 */
public class Line {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double opacity; //this is what determines how strong the line is, as opacity approaches 0, the line gets lighter
    private Color c;;
    private int initialRed;
    private int initialGreen;
    private int initialBlue;
    private int initialAlpha;
    private static final int MAX_OPACITY = 10;


    public Line(int x1, int y1, int x2, int y2, String type){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.opacity = MAX_OPACITY;
        switch (type){
            case "Emerald": this.c = new Color(0, 255, 0);
                break;
            case "Ruby": this.c = new Color(255, 0, 0);
                break;
            case "Sapphire": this.c = new Color(0, 0, 255);
                break;
            case "Topaz": this.c = new Color(255, 255, 0);
                break;
            case "Diamond": this.c = new Color(128, 128, 128);
                break;
            case "Aquamarine": this.c = new Color(0, 255, 255);
                break;
            case "Opal": this.c = new Color(127, 177, 117);
                break;
            case "Amethyst": this.c = new Color(255, 0, 255);
                break;
        }
        initialRed = c.getRed();
        initialGreen = c.getGreen();
        initialBlue = c.getBlue();
        initialAlpha = 255;
    }

    public double getAlpha(){
        return c.getAlpha();
    }

    public void fadeColor(){
        if (opacity >= 0) {
//            int newRed = (int) (initialRed + ((255 - initialRed) * (1 - (opacity / MAX_OPACITY))));
//            int newGreen = (int) (initialGreen + ((255 - initialGreen) * (1 - (opacity / MAX_OPACITY))));
//            int newBlue = (int) (initialBlue + ((255 - initialBlue) * (1 - (opacity / MAX_OPACITY))));
            int newAlpha =(int) (initialAlpha - (initialAlpha * (1 - (opacity / MAX_OPACITY))));
            c = new Color(initialRed, initialGreen, initialBlue, newAlpha);
            opacity--;
        }
    }

    public void draw(Graphics g){
        g.setColor(c);
        g.drawLine(x1, y1, x2, y2);
        fadeColor();
    }
}

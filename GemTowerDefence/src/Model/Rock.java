package Model;

import java.awt.*;

/**
 * Created by Sebastian on 2015-07-26.
 */

public class Rock extends Sprite{

    private int xpos;
    private int ypos;

    public Rock(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getXpos() {
        return xpos;
    }
    public int getYpos() {
        return ypos;
    }

    @Override
    public int getClassNumber() {
        return 4;
    }

    @Override
    public Point getPoint() {
        return new Point(xpos, ypos);
    }
}

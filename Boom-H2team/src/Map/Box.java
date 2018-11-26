package Map;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Box {
    /**
     * Box chứa x,y,width,height,type của các các hộp
     * có phương thức để vẽ
     */
    DrawableInterface drawable;
    public static int ALLROW_BANG = 0;
    public static int DISALLROW_BANG = 1;
    protected int x, y, width, height, type;
    protected Image img;

    public Box(int x, int y, int type, String images) {
        super();
        this.x = x;
        this.y = y;
        this.type = type;
        this.img = new ImageIcon(getClass().getResource(images)).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);

    }

    public void drawBox(Graphics2D g2d) {
        drawable = new DrawBoxImpl();
        drawable.draw(this, g2d);

    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType() {
        return type;
    }
}
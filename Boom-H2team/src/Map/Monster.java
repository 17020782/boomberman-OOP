package Map;

import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Monster {

    public  int x,y;
    public  int orient;
    public  int speed;
    public   int type = 5;
    protected int heart;
    protected Image img;
    public  int width, height;
    public static final int MonMIN = 5;
    public static final int MonMAX = 6;
    public static final int MinBOSS = 7;
    public static final int BOSS = 8;
    public static final int GHOST = 9;


    public Monster(int x, int y, int type, int orient, int speed, int heart, String images) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.orient = orient;
        this.speed = speed;
        this.heart = heart;
        this.img = new ImageIcon(getClass().getResource(images)).getImage();
        width = img.getWidth(null);
    }

    public void drawActor(Graphics2D g2d) {
    };

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOrient() {
        return orient;
    }

    public  void setOrient(int orient) {
        this.orient = orient;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static int getMonMIN() {
        return MonMIN;
    }

    public static int getMonMAX() {
        return MonMAX;
    }

    public static int getMinBOSS() {
        return MinBOSS;
    }

    public static int getBOSS() {
        return BOSS;
    }

    public static int getGHOST() {
        return GHOST;
    }

    /*  public void setChange(int orient) {
        super.changeOrient(orient);
    }*/
}

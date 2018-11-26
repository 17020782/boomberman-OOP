package Object;import Map.Manager;import javax.imageio.ImageIO;import javax.swing.text.DefaultCaret;import java.awt.*;import java.awt.image.BufferedImage;import java.io.File;import java.io.IOException;public class Bom extends BomberMan{    /**     * class đối tượng bom     */    private int posXBom;    private int posYBom;    private float time;    private boolean setBom;    private boolean ignite;                 // thuộc tính nổ hay chưa    private BomFlash bomflash;    private BufferedImage image;    public Bom(int posX, int posY, int width, int height, int speedUpDown, int speedRightLef,Manager manager){        super(posX,posY,  width, height,speedUpDown,speedRightLef);        this.manager = manager;        setBom = false;        ignite = false;        try {            image = ImageIO.read(new File("image/bomb.gif"));        } catch (IOException e) {            e.printStackTrace();        }    }    public void flashbom(){        System.out.println(manager.buff.getSizeBom());        bomflash = new BomFlash(this.getPosXBom(),this.getPosYBom(),manager,manager.buff.getSizeBom());    }    public void draw(Graphics2D g2){        manager.drawAllItem(g2);        manager.drawAllBox(g2);        super.draw(g2);        if(setBom == true){            g2.drawImage(image,this.getPosXBom(),this.getPosYBom(),null);        }        if(ignite){            bomflash.draw(g2);            bomflash.update(this.getPosXBom(),this.getPosYBom(),manager);        }    }    public void update(long now){        this.update();        if(isIgnite()) {        }        if(isSetBom()) {            if (now / 1000000000 - getTime() / 1000000000 > 2) {                setSetBom(false);                setIgnite(true);            }        }        if(now / 1000000000 - getTime() / 1000000000 > 3){            setIgnite(false);        }    }    public boolean impactionBomberBox(){        Rectangle rectangleBomber = new Rectangle(getPosX(),getPosY(),getWidth(),getHeight());        Rectangle rectangle = new Rectangle();        for(int i=0; i<manager.getArrBox().size(); i++){            Rectangle rectangleBox = new Rectangle(manager.getArrBox().get(i).getX(), manager.getArrBox().get(i).getY(), 45,45);            if(rectangleBomber.intersects(rectangleBox)){                rectangleBox.intersect(rectangleBox,rectangleBomber,rectangle);                if(rectangle.y > 23) return true;                else return false;            }        }        return false;    }    /**     * các hàm get set     * @return     */    public boolean isIgnite() {        return ignite;    }    public void setIgnite(boolean ignite) {        this.ignite = ignite;    }    public BufferedImage getImage() {        return image;    }    public void setImage(BufferedImage image) {        this.image = image;    }    public boolean isSetBom() {        return setBom;    }    public void setSetBom(boolean setBom) {        this.setBom = setBom;    }    public int getPosXBom() {        return posXBom;    }    public void setPosXBom(int posXBom) {        this.posXBom = posXBom;    }    public int getPosYBom() {        return posYBom;    }    public void setPosYBom(int posYBom) {        this.posYBom = posYBom;    }    public float getTime() {        return time;    }    public void setTime(float time) {        this.time = time;    }}
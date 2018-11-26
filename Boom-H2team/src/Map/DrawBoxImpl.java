package Map;

import java.awt.Graphics2D;

public class DrawBoxImpl implements DrawableInterface {
    /**
     * Có vẻ lớp này dùng để vẽ
     */

    @Override
    public void draw(Object object, Graphics2D g2d) {
        if(object instanceof Box) {
            Box box = (Box) object;
            g2d.drawImage(box.img, box.x, box.y, null);
        }

    }
}

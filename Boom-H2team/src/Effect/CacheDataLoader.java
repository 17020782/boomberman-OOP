package Effect;import javax.imageio.ImageIO;import javax.swing.*;import java.awt.*;import java.awt.image.BufferedImage;import java.io.*;import java.util.Hashtable;public class CacheDataLoader {    /**     * tạo một lớp tĩnh đặt hàm khởi tạo trong private không cho tạo đối tượng     */    private static CacheDataLoader instance;    /**     * Hastable lưu dưới dạng key-value giống như map     * để  lấy frame theo tên cho dễ     */    private Hashtable<String, FrameImage> frameImages;    private Hashtable<String, Animation> animations;    /**     * 2 đường dẫn file chứa 2 file input làm frame từ image     */    private String frameFile = "data/frame.txt";    private String animationFile = "data/animation.txt";    private CacheDataLoader() {    }    /**     * ham cho sử dụng biến tĩnh instance không phụ thuộc đối tượng tham chiếu     *     * @return     */    public static CacheDataLoader getInstance() {        if (instance == null)            instance = new CacheDataLoader();        return instance;    }    /**     * ham load frame vào hashtable     */    public void LoadFrame() throws IOException {        frameImages = new Hashtable<>();        FileReader fr = new FileReader(frameFile);        BufferedReader br = new BufferedReader(fr);        String line = null;             // dung đọc từng dòng file        if (br.readLine() == null) {            System.out.println("No Data");            throw new IOException();        } else {            fr = new FileReader(frameFile);            br = new BufferedReader(fr);            while ((line = br.readLine()).equals("")) ;          // bo qua dòng trống            int n = Integer.parseInt(line);                     // chuyển sang kiểu int            for (int i = 0; i < n; i++) {                FrameImage frame = new FrameImage();                // image = null                while ((line = br.readLine()).equals("")) ;                frame.setName(line);                            // đọc đến tên frame                while ((line = br.readLine()).equals("")) ; // đề phòng có dòng trống                String[] str = line.split(" ");          // cắt string bởi dấu cách                String path = line;                while ((line = br.readLine()).equals("")) ;                str = line.split(" ");                int x = (int) Double.parseDouble(str[1]);                   //x 15 thì lấy số 15                while ((line = br.readLine()).equals("")) ;                str = line.split(" ");                int y = (int) Double.parseDouble(str[1]);                while ((line = br.readLine()).equals("")) ;                str = line.split(" ");                int w = (int) Double.parseDouble(str[1]);                while ((line = br.readLine()).equals("")) ;                str = line.split(" ");                int h = (int) Double.parseDouble(str[1]);                BufferedImage imageData = ImageIO.read(new File(path));                BufferedImage image = imageData.getSubimage(x, y, w, h);               // lay ảnh con trong ảnh to theo tọa dộ                //image = resizeImage(image,45,image.getHeight()*45/image.getWidth());                frame.setImage(image);                instance.frameImages.put(frame.getName(), frame);            }        }        br.close();    }    /**     * hàm lấy image thông qua key     *     * @param name     * @return     */    public FrameImage getFrameImage(String name) {        /**         * dùng cái copy ở class FrameImage để copy và return để k ảnh hưởng đến tham chiếu         */        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));        return frameImage;    }    public Animation getAnimation(String name) {        Animation animation = new Animation(instance.animations.get(name));        return animation;    }    public void LoadAnimation() throws IOException {        animations = new Hashtable<>();        FileReader fr = new FileReader(animationFile);        BufferedReader br = new BufferedReader(fr);        String line = null;        if (br.readLine() == null) {            System.out.println("No Data!");            throw new IOException();        } else {            fr = new FileReader(animationFile);            br = new BufferedReader(fr);            while ((line = br.readLine()).equals("")) ;          // bo qua dòng trống            int n = Integer.parseInt(line);                     // chuyển sang kiểu int            for (int i = 0; i < n; i++) {                Animation animation = new Animation();                while ((line = br.readLine()).equals("")) ;                animation.setName(line);                while ((line = br.readLine()).equals("")) ;                String[] str = line.split(" ");                for (int j = 0; j < str.length; j += 2) {                    animation.add(getFrameImage(str[j]), Double.parseDouble(str[j + 1]));                    instance.animations.put(animation.getName(), animation);                }            }        }        br.close();    }    /**     * ham Load chung     */    public void LoadData() throws IOException {        LoadFrame();        LoadAnimation();    }}
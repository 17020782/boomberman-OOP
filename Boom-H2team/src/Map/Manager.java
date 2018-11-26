package Map;

import Interface.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
    private ArrayList<Box> arrBox; // mảng chứa các Box
    public ArrayList<Monster> arrMonster;
    private ArrayList<Item> arrItem;

    private ArrayList<String> url = new ArrayList<>();// mảng chứa url của các Box

    private String Background;
    private int type;
    public  BuffItem buff;
    public Manager(int type,BuffItem buff) {
        this.type = type;
        this.buff = buff;
        innitManager();
    }

    public void innitManager() {
        innit("src/map1/BOX.txt", "src/map1/MONSTER.txt","src/map1/ITEM.txt"); // đường link dẫn đến file txt
    }

    public void innit(String pathBox, String pathMonster, String pathItem) {
        arrBox = new ArrayList<Box>();
        arrMonster = new ArrayList<Monster>();
        arrItem = new ArrayList<Item>();
        innitArrBox(pathBox);
        initarrMonster(pathMonster);
        innitArrItem(pathItem);
    }


    public void innitArrBox(String pathBox) {    // đọc file, lấy dữ liệu vào các mảng
        try {
            FileReader file = new FileReader(pathBox);
            BufferedReader input = new BufferedReader(file);
            Background = input.readLine();
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                String images = str[3];      // url đến các images
                url.add(images);
                Box box = new Box(x, y, type, images);
                arrBox.add(box);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initarrMonster(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                int orient = Integer.parseInt(str[3]);
                int speed = Integer.parseInt(str[4]);
                int heart = Integer.parseInt(str[5]);
                String images = str[6];
                //MonsterMax max = new MonsterMax(x, y, type, orient, speed, heart, images);
                Monster min = new MonsterMin(x, y, type, orient, speed, heart, images);
                //MonsterMinBoss minBoss = new MonsterMinBoss(x, y, type, orient, speed, heart, images);
                //MonsterGhost ghost = new MonsterGhost(x, y, type, orient, speed, heart, images);
                //MonsterBoss boss = new MonsterBoss(x, y, type, orient, speed, heart, images);
                /*if (type == Monster.MonMAX) {
                    arrMonster.add(max);
                }*/
                arrMonster.add(min);
                /*if (type == Monster.MonMIN) {
                    arrMonster.add(min);
                }*/
               /* if (type == Monster.MinBOSS) {
                    arrMonster.add(minBoss);
                }
                if (type == Monster.GHOST) {
                    arrMonster.add(ghost);
                }
                if (type == Monster.BOSS) {
                    arrMonster.add(boss);
                }*/

            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void innitArrItem(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String str[] = line.split(":");
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                int type = Integer.parseInt(str[2]);
                String images = str[3];
                Item item = new Item(x, y, type, images);
                arrItem.add(item);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawAllBox(Graphics2D g2d) {    // để vẽ all box
        for (int i = 0; i < arrBox.size(); i++) {
            arrBox.get(i).drawBox(g2d);
        }
    }
    public void drawAllItem(Graphics2D g2d) {
        for (int i = 0; i < arrItem.size(); i++) {
            arrItem.get(i).drawItem(g2d);
        }
    }
    public void drawAllMonster(Graphics2D g2d) {
        for (int i = 0; i < arrMonster.size(); i++) {
            arrMonster.get(i).drawActor(g2d);
        }
    }
    public ArrayList<Item> getArrItem(){
        return arrItem;
    }
    public ArrayList<Box> getArrBox() {
        return arrBox;
    }

    public ArrayList<Monster> getArrMonster() {
        return arrMonster;
    }

    int x[] = {1,1,1,1,1,1,1,1,1,1,1,1};
    int y[] = {0,0,0,0,0,0,0,0,0,0,0,0};

    public void enemyImpaction() {
        for (int i = 0; i < getArrMonster().size(); i++) {
            getArrMonster().get(i).setX(getArrMonster().get(i).getX() + x[i]);
            getArrMonster().get(i).setY(getArrMonster().get(i).getY() + y[i]);
            //System.out.println(getArrMonster().get(i).getX() + " " + getArrMonster().get(i).getY());
            if(getArrMonster().get(i).getX() < 45){              // giới hạn tướng bên trái
                getArrMonster().get(i).setX(45);
                if (i % 2 ==0) {
                    x[i] = 0;
                    y[i] = 1;
                }
                else{
                    x[i] = 0;
                    y[i] = -1;
                }
                //System.out.println("vua di vao tuong` ben trai");
                //getArrMonster().get(i).setX(45);
            }
            else if(getArrMonster().get(i).getX() > (720-45)){  // giới hạn tường bên phải
                getArrMonster().get(i).setX(720-45);
                if(i%2 == 0 ){
                    x[i] = 0;
                    y[i] = -1;
                }
                else{
                    x[i] = 0;
                    y[i] = 1;
                }
                //System.out.println(" di vao tuong ben phai");

            }
            else if(getArrMonster().get(i).getY() < 45){  // giới hạn tường bên trên
                //getArrMonster().get(i).setY(45);//setY(45);
                getArrMonster().get(i).setY(45);
                if ( i % 2 == 0 ) {
                    y[i] = 0;
                    x[i] = -1;
                }
                else {
                    y[i] = 0;
                    x[i] = 1;
                }
                //System.out.printf("di vao ben tren");
            }
            else if( getArrMonster().get(i).getY() > (630-45)){ // giới hạn tường phía dưới
                getArrMonster().get(i).setY(630-45);
                if ( i % 2==0) {
                    y[i] = 0;
                    x[i] = 1;
                }
                else{
                    y[i] = 0;
                    x[i] = -1;
                }
                //System.out.println("di vao tuong ben duoi");
            }
            for (int j = 0; j < getArrBox().size(); j++) {
                Rectangle rec1 = new Rectangle(getArrBox().get(j).getX(), getArrBox().get(j).getY(),
                        getArrBox().get(j).getWidth(), getArrBox().get(j).getHeight());
                Rectangle rec2 = new Rectangle(getArrMonster().get(i).getX(), getArrMonster().get(i).getY(), 45, 45);
                Rectangle rec3 = new Rectangle();
                if (rec1.intersects(rec2)){
                    rec1.intersect(rec1, rec2, rec3);
                    if (rec2.x > rec1.x  && rec3.height >= 40) {    // xử lí đi sang trái
                        getArrMonster().get(i).setX(getArrBox().get(j).getX()+45);
                        if( i%2 ==0 ) {
                            x[i] = 0;
                            y[i] = 1;
                        }
                        else{
                            x[i] = 0;
                            y[i] = -1;
                        }
                       // System.out.println("xu li sang trai" + rec1.x + " " +rec1.y);
                        break;
                    }

                    if (rec2.x < rec1.x && rec2.y <= rec1.y  && rec3.height >= 30 ) {    // xử lí đi sang phai
                        getArrMonster().get(i).setX(getArrBox().get(j).getX()-45);
                        if(i % 2 == 0) {
                            x[i] = 0;
                            y[i] = -1;
                        }
                        else{
                            x[i] = 0;
                            y[i] = 1;
                        }
                        //System.out.println(" xu li sang phai");
                        break;
                    }


                    if (rec2.y > rec1.y && rec3.width >= 5 && rec3.height <= 5 ) {    // xử lí đi lên trên
                        getArrMonster().get(i).setY(getArrBox().get(j).getY()+45);
                        if (i % 2 ==0 ) {
                            x[i] = -1;
                            y[i] = 0;
                        }
                        else{
                            x[i] = 1;
                            y[i] = 0;
                        }
                        //System.out.println("xu li len tren " + rec1.x +" " + rec1.y +" " + rec1.width+" " + rec1.height);
                        break;
                    }
                    if (rec2.y < rec1.y && rec2.x >= rec1.x && rec3.width >= 10 && rec3.height <= 5) {
                        getArrMonster().get(i).setY(getArrBox().get(j).getY()-45);
                        if(i % 2 ==0 ) {
                            x[i] = 1;
                            y[i] = 0;
                        }
                        else{
                            x[i] = -1;
                            y[i] = 0;
                        }
                        break;
                        //setPosY(manager.getArrBox().get(i).getY() - rec2.height);

                    }
                }
            }
            //System.out.println(getArrMonster().get(i).getX());

        }
        //System.out.println();
    }
}






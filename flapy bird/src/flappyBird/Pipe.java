package flappyBird;

import java.awt.*;

public class Pipe {
    private int x;
    private int y;
    private int width;

    private int height;

    private int velocityX = -4;
    private boolean passed = false;

    private Image img;
    public Pipe(int x, int y, Image img){
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height =512;
        this.img = img;
    }
    public void draw (Graphics g) {
        g.drawImage(img,x,y,width,height,null);
    }
    public void move() {
        x += velocityX;
    }
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
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

}

package flappyBird;

import java.awt.*;

public class Bird {
    private int x,y,width,height;

    private Image img;
    private int velocityY=0;
    private int gravity = 1;

    public Bird(int x,int y,Image img){
        this.x =x;
        this.y =y;
        this.img = img;
        this.width = 34;
        this.height = 24;
    }
public void draw(Graphics g){
        g.drawImage(img,x,y,width,height,null);
}
public void move(){
    velocityY +=gravity;
    y += velocityY;
    y = Math.max(y,0);
}
public void jump() {
    velocityY=-9;
}
public void reset(int x,int y){
    this.x = x;
    this.y = y;
    velocityY = 0;
}
    public boolean collidesWith(Pipe pipe){
        return x < pipe.getX() + pipe.getWidth() &&
                x + width > pipe.getX() &&
                y < pipe.getY() + pipe.getHeight() &&
                y + height > pipe.getY();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

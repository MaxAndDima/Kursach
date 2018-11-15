package com.mygdx.game;

/**
 * Created by Dima on 12.03.2018.
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    public double x;
    public double y;
    public Sprite rec;
    Rectangle rectangle;
    boolean active;

    public void updateBullet ()
    {
        rectangle.setX(rec.getX());
        rectangle.setY(rec.getY());
    }
    Bullet(int x1,int y1,int height, int width, double x2, double y2){
        active = true;
        Texture imgBullet = new Texture("bullet.png");
        x = x2;
        y = y2;
        rec = new Sprite(imgBullet, width, height);
        rec.setCenterX(x1);
        rec.setCenterY(y1);
        rectangle = new Rectangle(rec.getX(),rec.getY(),rec.getWidth(),rec.getHeight());
        if(y2 > 0) {
            rec.rotate((float) ((-1) * 57 * Math.asin(x)));
        }
        else
        {
            rec.rotate((float) (180 - ((-1) * 57 * Math.asin(x))));
        }

    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class t1 extends Enemy {

    static int ver = 120;
    t1(int x1, int y1, MainGameClass ptr, final GameScreen gameScreen) {
        super(x1, y1, ptr, gameScreen);

        textureRegion = new TextureRegion(new Texture("TrackAnim.png"));
        attackRad = 40;
        destrTime = 3;

        Texture img = new Texture("TankAnim.png");

        rec.setTexture(img);
        rec.setSize(32,24);

        animFier = new Animation(new TextureRegion(new Texture("FireAnim.png")),5,0.2f,true,rec.getX(), rec.getY());
        animMove = new Animation(new TextureRegion(img), 2, 0.2f,true, rec.getX(), rec.getY());
        animDestr = new Animation(new TextureRegion(new Texture("TankDestrAnim.png")), 5, 0.2f,true, rec.getX(), rec.getY());

        health = 1;
        damage = 1;
        speed = 100;

        rectangle = new Rectangle((int) (rec.getX()) ,(int) rec.getY(),(int) rec.getWidth(),(int) rec.getHeight());

        if(x < 400) {
            double temp;
            do {
                int val = ptr.random.nextInt();
                if (val > 0) {
                    sin = 1 / ((float) (val % 5 + 2));
                } else {
                    sin = 1 / ((float) (val % 5 - 2));
                }
                cos = (float) (Math.sqrt(1 - sin * sin));
                temp = y + 400 * (sin/cos);
            } while((temp < 20)||(temp > 460));
            rec.rotate((float) (Math.asin(sin) * 57));
        }
        else
        {
            double temp;
            do {
                int val = ptr.random.nextInt();
                if(val > 0)
                {
                    sin = 1 / ((float)(val % 5 + 2));
                }
                else
                {
                    sin = 1 / ((float)(val % 5 - 2));
                }
                cos = (float) ((-1) * Math.sqrt(1 - sin * sin));
                temp = y - 400 * (sin/cos);
            } while((temp < 20)||(temp > 460));
            rec.rotate((float) (180 - Math.asin(sin) * 57));
        }

    }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Dima on 17.03.2018.
 */

public class Gun {
    double grad;
    public Sprite rec;
    Texture texture;
    Texture img;

    public Gun()
    {
        grad = 0;
        img = new Texture("pushka.png");
        rec = new Sprite(img,30,64);
        texture = new Texture("stand.png");
        rec.setX(385);
        rec.setY(208);
    }

    public void update(float touchX, float touchY)
    {
        double buff;
        buff = grad;
        if(touchY >= 0)
        {
            grad =  -90 + (57 * Math.acos(touchX/(Math.sqrt(touchX*touchX + touchY*touchY))));
        }
        else
        {
            grad =  -90 - (57 * Math.acos(touchX/(Math.sqrt(touchX*touchX + touchY*touchY))));
        }
        rec.rotate((float) (grad - buff));
    }
}

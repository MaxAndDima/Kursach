package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Dima on 21.03.2018.
 */


public class Mamonth extends Enemy {
    static int ver = 5;

    @Override
    public int getType()
    {
        return 2;
    }

    @Override
    protected  void AppendFire()
    {
        Sprite sprite = new Sprite(animFier.getFrame());
        sprite.setBounds(Sprites.get(0).getX()+20,Sprites.get(0).getY()+65,36,39);
        Sprites.add(sprite);
        sprite = new Sprite(animFier.getFrame());
        sprite.setBounds(Sprites.get(0).getX()+50,Sprites.get(0).getY()+55,24,26);
        Sprites.add(sprite);
    }
    @Override
    protected  void UpdateFire()
    {
        Sprites.get(Sprites.size() - 1).setRegion(animFier.getFrame());
        Sprites.get(Sprites.size() - 2).setRegion(animFier.getFrame());
    }
    Mamonth(int x1, int y1, MainGameClass ptr, final GameScreen gameScreen) {
        super(x1, y1, ptr, gameScreen);
        attackRad = 150;
        destrTime = 6;

        Texture img = new Texture("MamonthTankIdle.png");

        rec.setTexture(img);
        rec.setSize(76,118);
        rectangle = new Rectangle((int) (rec.getX()) ,(int) rec.getY(),(int) rec.getWidth(),(int) rec.getHeight());

        textureRegion = new TextureRegion(new Texture("TrackAnim.png"));

        animMove = new Animation(new TextureRegion(img), 1, 0.2f,true, rec.getX(), rec.getY());
        animDestr = new Animation(new TextureRegion(new Texture("MamonthTankDestroyed.png")), 1, 0.2f,true, rec.getX(), rec.getY());

        health = 5;
        damage = 3;
        speed = 20;

        if(x < 400) {
            rec.rotate((float) (-90));
            sin = 0;
            cos = 1;
        }
        else
        {
            rec.rotate((float) (90));
            sin = 0;
            cos = -1;
        }
    }

}

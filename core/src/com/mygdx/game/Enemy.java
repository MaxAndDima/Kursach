package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Dima on 15.03.2018.
 */

public class Enemy {

    final GameScreen gs;
    int attackRad;
    int state;
    //state = 0, танк стоит
    //state = 1, танк едет
    //state = 2, танк взорван
    int x;
    int y;
    float destrTime;
    float sin;
    float cos;
    int damage;
    float speed;
    int health;
    public Sprite rec;
    public Rectangle rectangle;
    boolean active;
    final MainGameClass ptr;
    Animation animFier;
    Animation animMove;
    Animation animDestr;
    Animation animAttack;
    ArrayList<Sprite> Sprites;
    TextureRegion textureRegion;


    static int ver = 120;

    float trackTime;
    ArrayList<AnimDelay> arrAnimTrack;

    public void drawEnemy(float dt, SpriteBatch batch)
    {
        for(int j = 0; j < arrAnimTrack.size(); j++)
        {
            arrAnimTrack.get(j).update(dt);
            if(arrAnimTrack.get(j).frame == -1)
            {
                //arrAnimExplosive.remove(i);
                continue;
            }
            batch.draw(arrAnimTrack.get(j).getFrame(), arrAnimTrack.get(j).x, arrAnimTrack.get(j).y,
                    arrAnimTrack.get(j).getFrame().getRegionWidth() / 2,arrAnimTrack.get(j).getFrame().getRegionHeight() / 2,
                    arrAnimTrack.get(j).getFrame().getRegionWidth(),arrAnimTrack.get(j).getFrame().getRegionHeight(), 1, 1,
                    rec.getRotation());
            //arrAnimExplosive.get(i).update(delta);

        }
        for(int j=0;j<Sprites.size();j++)
            Sprites.get(j).draw(batch);
    }
    protected void AppendFire()
    {
        Sprite sprite = new Sprite(animFier.getFrame());
        sprite.setX(Sprites.get(0).getX() + rec.getWidth() / 2);
        sprite.setY(Sprites.get(0).getY() + rec.getHeight() / 2);
        Sprites.add(sprite);
    }
    protected void UpdateFire()
    {
        //for (int i=1;i<Sprites.size();i++)
            Sprites.get(Sprites.size() - 1).setRegion(animFier.getFrame());
    }

    public int getType()
    {
        return 0;
    }

    public void updateEnemy (float dt)
    {
        if (health <= 0 && (state != 2)) {
            state = 2;
            gs.enemyKilledType(getType());
        }
        if(state == 0)
        {
            gs.damage(damage * dt);
        }
        if(state == 1)
        {

            if ((rec.getX() < 400 - attackRad - rec.getWidth()) || (rec.getX() > 400 + attackRad)) {
                rec.setX(rec.getX() + speed * cos * dt);
                rec.setY(rec.getY() + speed * sin * dt);
            }
            else {
                state = 0;
            }



            rec.setRegion(animMove.getFrame());
            if(trackTime > 0)
            {
                trackTime -= dt;
            }
            else
            {
                //Sprite spr = new Sprite(arrAnimTrack.get(arrAnimTrack.size() - 1).getFrame());
                //spr.rotate(rec.getRotation());
                arrAnimTrack.add(new AnimDelay(textureRegion, 15, 2,false, (rec.getX() - Math.abs(sin) * rec.getHeight() / 2), rec.getY() - rec.getHeight() * Math.abs(cos) / 2, 2.5f));
                //Sprites.add(spr);
                trackTime = 15/speed;
            }
            rectangle.setX(rec.getX());
            rectangle.setY(rec.getY());

        }
        if(state == 2)
        {
            if (Sprites.size()==1)
            {
                AppendFire();
            }
            //rec.setTexture(animDestr.getFrame().getTexture());
            if(destrTime > 0)
            {

                animDestr.update(dt);
                animFier.update(dt);
                rec.setRegion(animDestr.getFrame());
                UpdateFire();
                destrTime -= dt;

            }
            else
            {
                active = false;
            }

        }


    }
    Enemy(int x1, int y1, final MainGameClass ptr, final GameScreen gameScreen)
    {

        state = 1;
        this.ptr = ptr;

        arrAnimTrack = new ArrayList<AnimDelay>();
        Sprites = new ArrayList<Sprite>();

        active = true;

        x = x1;
        y = y1;

        rec = new Sprite();
        rec.setCenterX(x1);
        rec.setCenterY(y1);
        rec.setOriginCenter();

        animFier = new Animation(new TextureRegion(new Texture("FireAnim.png")),5,0.2f,true,rec.getX(), rec.getY());
        //Gdx.app.debug("Enemy", "rec.with = " +  rec.getWidth() + " rec.Height = " + rec.getHeight() + " rectangle.with = " + rectangle.width + "rectangle.height = " + rectangle.height);
        Sprites.add(rec);
        gs = gameScreen;
    }
}

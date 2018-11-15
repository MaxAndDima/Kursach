package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class t2 extends Enemy {

    // state = 3 - подготовка к стрельбе
    // state = 4 - готовы к стрельбе
    // state = 5 - тупим
    // state = 6 - вычисляем куда ехать
    // state = 7 - поворот
    // state = 8 - едем
    int rotateluft;
    int rotateSpeed;
    float tupleniaTime;
    Timer tupleniaTimer;
    int pos;
    Texture img;
    static int ver = 30;

    @Override
    public void updateEnemy (float dt) {

        if (health <= 0 && (state != 2)) {
            state = 2;
            gs.enemyKilledType(getType());
        }
        if(state == 3)
        {
            //rec.setOrigin(rec.getWidth()/2,rec.getHeight()/2);
            if(x > 400)
            {
                if(180 - rec.getRotation() < -rotateluft)
                {
                    rec.rotate(-rotateSpeed * dt);
                }
                else if(180 - rec.getRotation() > rotateluft)
                {
                    rec.rotate(rotateSpeed * dt);
                }
                else
                {
                    state = 4;
                }
            }
            else
            {
                if(0 - rec.getRotation() < -rotateluft)
                {
                    rec.rotate(-rotateSpeed * dt);
                }
                else if(0 - rec.getRotation() > rotateluft)
                {
                    rec.rotate(rotateSpeed * dt);
                }
                else
                {
                    state = 4;
                }
            }
        }
        if(state == 4)
        {
            gs.damage(damage);
            animAttack.frame = 0;
            state = 5;
        }
        if(state == 5)
        {
            if(animAttack.frame != -1)
            {
                animAttack.update(dt);
                rec.setRegion(animAttack.getFrame());
            }
            else
            {
                rec.setTexture(img);
            }
            if(tupleniaTimer.updateTimer(dt))
            {
                rec.setTexture(img);
                state = 6;
            }
        }
        if(state == 6)
        {
            do{
                pos = Math.abs(ptr.random.nextInt()) % 400  + 40;
            }while(Math.abs(pos - rec.getY()) < 100);
            if(pos > rec.getY())
            {
                sin = 1;
                cos = 0;
            }
            else
            {
                sin = -1;
                cos = 0;
            }
            state = 7;
        }
        if(state == 7)
        {
            //rec.setOrigin(rec.getWidth()/2,rec.getHeight()/2);
            if(x > 400)
            {
                if(pos < rec.getY())
                {
                    if(270 - rec.getRotation() < -rotateluft)
                    {
                        rec.rotate(-rotateSpeed * dt);
                    }
                    else if(270 - rec.getRotation() > rotateluft)
                    {
                        rec.rotate(rotateSpeed * dt);
                    }
                    else
                    {
                        state = 8;
                    }
                }
                else
                {
                    if(90 - rec.getRotation() < -rotateluft)
                    {
                        rec.rotate(-rotateSpeed * dt);
                    }
                    else if(90 - rec.getRotation() > rotateluft)
                    {
                        rec.rotate(rotateSpeed * dt);
                    }
                    else
                    {
                        state = 8;
                    }
                }
            }
            else
            {
                if(pos < rec.getY())
                {
                    if(-90 - rec.getRotation() < -rotateluft)
                    {
                        rec.rotate(-rotateSpeed * dt);
                    }
                    else if(-90 - rec.getRotation() > rotateluft)
                    {
                        rec.rotate(rotateSpeed * dt);
                    }
                    else
                    {
                        state = 8;
                    }
                }
                else
                {
                    if(90 - rec.getRotation() < -rotateluft)
                    {
                        rec.rotate(-rotateSpeed * dt);
                    }
                    else if(90 - rec.getRotation() > rotateluft)
                    {
                        rec.rotate(rotateSpeed * dt);
                    }
                    else
                    {
                        state = 8;
                    }
                }
            }
        }

        if(state == 8)
        {
            if (Math.abs(pos - rec.getY()) > 5) {
                rec.setX(rec.getX() + speed * cos * dt);
                rec.setY(rec.getY() + speed * sin * dt);
            }
            else {
                state = 3;
            }

            rec.setRegion(animMove.getFrame());
            if (trackTime > 0) {
                trackTime -= dt;
            } else {
                arrAnimTrack.add(new AnimDelay(textureRegion, 15, 2, false,
                        (rec.getX() - Math.abs(sin) * rec.getHeight() / 2), rec.getY() - rec.getHeight() * Math.abs(cos) / 2, 2.5f));
                trackTime = 15 / speed;
            }
            rectangle.setX(rec.getX());
            rectangle.setY(rec.getY());
        }


        if (state == 1) {

            if ((rec.getX() < 400 - attackRad - rec.getWidth()) || (rec.getX() > 400 + attackRad)) {
                rec.setX(rec.getX() + speed * cos * dt);
                rec.setY(rec.getY() + speed * sin * dt);
            }
            else {
                state = 3;
            }

            rec.setRegion(animMove.getFrame());
            if (trackTime > 0) {
                trackTime -= dt;
            } else {
                arrAnimTrack.add(new AnimDelay(textureRegion, 15, 2, false, (rec.getX() - Math.abs(sin) * rec.getHeight() / 2), rec.getY() - rec.getHeight() * Math.abs(cos) / 2, 2.5f));
                trackTime = 15 / speed;
            }
            rectangle.setX(rec.getX());
            rectangle.setY(rec.getY());
        }
        if (state == 2) {
            if (Sprites.size() == 1) {
                AppendFire();
            }
            //rec.setTexture(animDestr.getFrame().getTexture());
            if (destrTime > 0) {

                animDestr.update(dt);
                animFier.update(dt);
                rec.setRegion(animDestr.getFrame());
                UpdateFire();
                destrTime -= dt;

            } else {
                active = false;
            }

        }
    }

    t2(int x1, int y1, MainGameClass ptr, final GameScreen gameScreen) {
        super(x1, y1, ptr, gameScreen);

        rotateluft = 2;
        rotateSpeed = 45;
        tupleniaTime = 3;
        tupleniaTimer = new Timer(tupleniaTime);
        textureRegion = new TextureRegion(new Texture("TrackAnim.png"));
        attackRad = 200;
        destrTime = 3;

        img = new Texture("t2anim.png");

        rec.setTexture(img);
        rec.setSize(40,32);

        animFier = new Animation(new TextureRegion(new Texture("FireAnim.png")),5,0.2f,true,rec.getX(), rec.getY());
        animMove = new Animation(new TextureRegion(img), 2, 0.2f,true, rec.getX(), rec.getY());
        animDestr = new Animation(new TextureRegion(new Texture("t2anim.png")), 2, 0.2f,true, rec.getX(), rec.getY());
        animAttack = new Animation(new TextureRegion(new Texture("t2attack.png")), 2, 1.0f,false, rec.getX(), rec.getY());

        health = 2;
        damage = 5;
        speed = 70;

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
        rec.setOrigin(rec.getWidth()/2,rec.getHeight()/2);
    }
}

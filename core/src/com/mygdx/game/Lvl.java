package com.mygdx.game;

public class Lvl extends GameScreen {
    int t1Count;
    int t2Count;
    int mamonthCount;
    int mamonthVer;
    int t1Ver;
    int t2Ver;
    int lvlNumber;
    public Lvl(MainGameClass gam, MainMenuScreen gc, double tED, int mamonthCountl, int t2Countl, int t1Countl, int number) {
        super(gam, gc);
        win = false;
        lvlNumber = number;
        t1Count = 0;
        t2Count = t2Countl;
        mamonthCount = mamonthCountl;
        timeEnemyDelay = tED;
        mamonthVer = Mamonth.ver;
        t1Ver = t1.ver;
        t2Ver = t2.ver;
    }
    @Override
    public int getLvl()
    {
        return lvlNumber;
    }

    @Override
    public void enemyKilledType(int type)
    {
        if(type == 0)
        {
            //t2Count--;
        }
        else if(type == 1)
        {
            //t1Count--;
        }
        else if(type == 2)
        {
            //mamonthCount--;
        }
    }

    @Override
    public void spawnEnemy()
    {
        Enemy enemy;
        int type;
        int value = 0;
        if(mamonthCount > 0)
        {
            value+=mamonthVer;
        }
        if(t1Count > 0)
        {
            value+=t1Ver;
        }
        if(t2Count > 0)
        {
            value+=t2Ver;
        }
        if(value == 0)
        {
            if(arrEnemy.isEmpty())
            {
                win = true;
            }
            return;
        }
        type = Math.abs(game.random.nextInt()) % (value);

        int x;
        if(game.random.nextBoolean())
        {
            x=-150;
        }
        else
        {
            x=850;
        }
        if ((mamonthCount > 0)&&(type=type-mamonthVer)<=0)
        {
            enemy = new Mamonth(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
            mamonthCount--;
        }
        else if ((t2Count > 0)&&(type=type-t2Ver)<=0)
        {
            enemy = new t2(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
            t2Count--;
        }
        else if ((t1Count > 0)&&(type=type-t1Ver)<=0)
        {
            enemy = new t1(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
            t1Count--;
        }
        else
        {
            enemy = new t1(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
            t1Count--;
        }
        arrEnemy.add(enemy);
    }
}

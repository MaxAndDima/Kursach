package com.mygdx.game;

public class Timer {
    float t1;
    float t2;
    Timer(float time)
    {
        t1 = time;
        t2 = time;
    }
    public boolean updateTimer(float dt)
    {
        if(t1 > 0)
        {
            t1 -= dt;
            return false;
        }
        else
        {
            t1 = t2;
            return true;
        }
    }
}

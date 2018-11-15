package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dima on 20.03.2018.
 */

public class AnimDelay extends Animation {
    float delay;
    public AnimDelay(TextureRegion region, int frameCount, float cycleTime, boolean cycle, float x, float y, float delay) {
        super(region, frameCount, cycleTime, false, x, y);
        this.delay = delay;
    }
    @Override
    public void update(float dt)
    {
        if(delay > 0)
        {
            delay -= dt;
            return;
        }
        if(frame == -1)
        {
            return;
        }
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime)
        {
            currentFrameTime = 0;
            frame++;
        }
        if(frame >= frameCount)
        {
            if(cycle)
            {
                frame = 0;
            }
            else
            {
                frame = -1;
            }
        }
    }
}

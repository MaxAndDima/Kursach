package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Dima on 18.03.2018.
 */

public class Animation {
    protected Array<TextureRegion> frames;
    protected float maxFrameTime;
    protected float currentFrameTime;
    protected int frameCount;
    public int frame;
    protected boolean cycle;
    public int x;
    public int y;

    public Animation(TextureRegion region, int frameCount, float cycleTime , boolean cycle, float x, float y) {

        this.cycle = cycle;
        currentFrameTime = 0;
        frames = new Array<TextureRegion> ();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++)
        {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
    }
        this.frameCount = frameCount;
        frame = 0;
        maxFrameTime = cycleTime / frameCount;
        this.x = (int)x + 30 - frameWidth / 2;
        this.y = (int)y + 25 - region.getRegionHeight() / 2;
    }
    public void update(float dt)
    {
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
    public TextureRegion getFrame()
    {
       if(frame != -1){
           return frames.get(frame);
       }
       return frames.get(0);
    }
}


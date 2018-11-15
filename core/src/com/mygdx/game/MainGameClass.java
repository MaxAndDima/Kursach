package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dima on 17.03.2018.
 */

public class MainGameClass extends Game {
    SpriteBatch batch;
    BitmapFont font;
    int record;
    boolean start;
    boolean setRecord;
    TextureRegion trackTexture;
    Random random;
    ArrayList<Boolean> lvlsProgress;

    public void create() {
        lvlsProgress = new ArrayList<Boolean>();
        for(int i = 0; i < 3; i++)
        {
            lvlsProgress.add(Boolean.FALSE);
        }
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        random = new Random();
        trackTexture = new TextureRegion(new Texture("explosive.png"));
        setRecord = false;
        start = true;
        record = 0;
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }
    public void gameOver(int value)
    {
        if(value > record)
        {
            record = value;
            setRecord = true;
        }

    }
}

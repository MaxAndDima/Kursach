package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by Dima on 17.03.2018.
 */

public class MainMenuScreen implements Screen {

    final MainGameClass game;
    OrthographicCamera camera;
    int width = 800;
    int height = 480;
    ArrayList<Sprite> arrBtn;
    Vector3 touchPos;
    GameScreen gs;

    public void GameOver()
    {
        game.setScreen(this);
        GameScreen gam;
        gam = gs;
        gam.dispose();
    }
    public MainMenuScreen(final MainGameClass gam) {
        this.game = gam;

        Sprite btnLvls = new Sprite(new Texture("btnLvls.png"),240,75);
        btnLvls.setX((width / 2) - (btnLvls.getWidth() / 2));
        btnLvls.setY((height / 2) - (btnLvls.getHeight() / 2) + 180);

        Sprite btnStart = new Sprite(new Texture("btnStart.png"),240,75);
        btnStart.setX((width / 2) - (btnStart.getWidth() / 2));
        btnStart.setY((height / 2) - (btnStart.getHeight() / 2) + 60);

        Sprite btnRecord = new Sprite(new Texture("btnRecord.png"),240,75);
        btnRecord.setX((width / 2) - (btnRecord.getWidth() / 2));
        btnRecord.setY((height / 2) - (btnRecord.getHeight() / 2) - 60);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        arrBtn = new ArrayList<Sprite>();
        arrBtn.add(btnLvls);
        arrBtn.add(btnStart);
        arrBtn.add(btnRecord);

        touchPos = new Vector3();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        if(game.setRecord)
        {
            game.font.draw(game.batch, "NEW RECORD!", 400, 400);
            game.font.draw(game.batch, "" + game.record, 400, 380);
        }
        for(int i = 0; i < arrBtn.size(); i++)
        {
            arrBtn.get(i).draw(game.batch);
        }
        game.batch.end();

        if(Gdx.input.isTouched())
        {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            int index = -1;
            for(int i = 0; i < arrBtn.size(); i++)
            {
                if((touchPos.x > arrBtn.get(i).getX())&&(touchPos.x < arrBtn.get(i).getX() + arrBtn.get(i).getWidth()) &&
                        (touchPos.y > arrBtn.get(i).getY())&&(touchPos.y < arrBtn.get(i).getY() + arrBtn.get(i).getHeight()))
                {
                    index = i;
                }
            }
            if(index == 0)
            {
                game.setScreen(new LvlMenuScreen(game, camera, this));
            }
            else if(index == 1)
            {
                game.setRecord = false;
                gs = new GameScreen(game, this);
                game.setScreen(gs);

            }
            else if (index == 2)
            {
                // Экран рекордов
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

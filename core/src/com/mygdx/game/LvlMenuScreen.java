package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class LvlMenuScreen implements Screen {

    final MainGameClass game;
    OrthographicCamera camera;
    int width = 800;
    int height = 480;
    ArrayList<Sprite> arrBtn;
    Vector3 touchPos;
    MainMenuScreen mainMenuScreen;
    boolean flag;
    public LvlMenuScreen (final MainGameClass gam, OrthographicCamera cam, MainMenuScreen mc)
    {
        flag = true;
        mainMenuScreen = mc;
        camera = cam;
        this.game = gam;
        Texture txt1 = new Texture("lvl.png");
        Texture txt2 = new Texture("lvlvin.png");
        Texture txt3;
        arrBtn = new ArrayList<Sprite>();
        for(int i = 0; i < game.lvlsProgress.size(); i++) {
            if(game.lvlsProgress.get(i))
            {
                txt3 = txt2;
            }
            else
            {
                txt3 = txt1;
            }
            Sprite btnlvl1 = new Sprite(txt3, 75, 75);
            btnlvl1.setX((width / 2) - (btnlvl1.getWidth() / 2) - 240 + 120 * (i%5));
            btnlvl1.setY((height / 2) - (btnlvl1.getHeight() / 2) + 120 - 120 * (i/5));
            arrBtn.add(btnlvl1);
        }

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
            //game.font.draw(game.batch, "NEW RECORD!", 400, 400);
            //game.font.draw(game.batch, "" + game.record, 400, 380);
        }
        for(int i = 0; i < arrBtn.size(); i++)
        {
            arrBtn.get(i).draw(game.batch);
        }
        game.batch.end();

        if(Gdx.input.isTouched())
        {
            if(!flag)
            {
                flag = true;
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
                Gdx.app.debug("Log", "index = " + index);
                if(index == 0)
                {
                    mainMenuScreen.gs = new Lvl(game, mainMenuScreen, 2, 0, 2,3, index);
                    game.setScreen(mainMenuScreen.gs);
                }
                else if(index == 1)
                {
                    mainMenuScreen.gs = new Lvl(game, mainMenuScreen, 1, 2, 10,5, index);
                    game.setScreen(mainMenuScreen.gs);
                }
                else if (index == 2)
                {
                    mainMenuScreen.gs = new Lvl(game, mainMenuScreen, 0.8, 15, 30,10, index);
                    game.setScreen(mainMenuScreen.gs);
                }
            }
        }
        else
        {
            flag = false;
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

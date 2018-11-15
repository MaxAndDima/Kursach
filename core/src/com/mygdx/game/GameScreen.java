package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class GameScreen implements Screen {

	//Sound ExplosiveSound;
	final MainGameClass game;
	int count;
	SpriteBatch batch;
	Texture imgBullet;
	Texture imgBack;
	Texture ZABOR;
	OrthographicCamera camera;
	ArrayList<Bullet> arrBullet;
	ArrayList<Enemy> arrEnemy;
	boolean flag;
	double time;
	double cooldown;
	double timeEnemy;
	double healthZABOR;
	BitmapFont font;
	int enemyKilled;
	MainMenuScreen gc;
	Gun gun;
	ArrayList<Animation> arrAnimExplosive;
	TextureRegion tr;
	double timeEnemyDelay;
	float diff;
	boolean win;


	public void spawnBullet(double x, double y){
		double a = x/(Math.sqrt(x*x + y*y));
		double b = y/(Math.sqrt(x*x + y*y));
		Bullet bullet = new Bullet(400, 240,18,10, a, b);
		arrBullet.add(bullet);

	}

	public int getLvl(){
	return -1;
	}

	public void damage(float val){
		healthZABOR -= val;
	}

	public void enemyKilledType(int type){

	}

	public void spawnEnemy(){
		Enemy enemy;
		int type = Math.abs(game.random.nextInt()) % (Mamonth.ver + t1.ver + t2.ver);
		int x;
		if(game.random.nextBoolean())
		{
			x=-150;
		}
		else
		{
			x=850;
		}
		if ((type=type-Mamonth.ver)<=0)
			enemy = new Mamonth(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
		else if ((type=type-t2.ver)<=0)
			enemy = new t2(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
		else if ((type=type-t1.ver)<=0)
			enemy = new t1(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);
		else
			enemy = new t1(x, Math.abs(game.random.nextInt()) % 400 + 40, game, this);

		arrEnemy.add(enemy);

	}


	public GameScreen (final MainGameClass gam, MainMenuScreen gc) {

		win = false;
		timeEnemyDelay = 1;
		diff = 1;
		arrAnimExplosive = new ArrayList<Animation>();
		gun = new Gun();
		this.gc = gc;
		this.game = gam;
		tr = new TextureRegion(new Texture("explosive.png"));

		count = 0;
		enemyKilled = 0;
		font = new BitmapFont();
		healthZABOR = 100;
		time = 0;
		timeEnemy = 1;
		cooldown = 0.15;
		flag = false;
		arrEnemy = new ArrayList<Enemy>();
		arrBullet = new ArrayList<Bullet> ();

		//ExplosiveSound = Gdx.audio.newSound(Gdx.files.internal("ExplosiveSound.mp3"));
		imgBack = new Texture("BackTest.jpg");
		batch = new SpriteBatch();
		imgBullet = new Texture("bullet.png");
		ZABOR = new Texture("ZABOR.png");
	}

	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {


		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,480);

		game.batch.begin();

		game.batch.draw(imgBack, 0, 0);
		game.batch.draw(ZABOR, (392), (0));
		game.batch.draw(gun.texture,(371),(209));
		gun.rec.draw(game.batch);
		for(int i = 0; i < arrBullet.size(); i++)
		{
			if(arrBullet.get(i).active)
				arrBullet.get(i).rec.draw(game.batch);
			else
			{
				arrBullet.remove(i);
			}
		}
		for(int i = 0; i < arrEnemy.size(); i++)
		{
			if(arrEnemy.get(i).active){
				arrEnemy.get(i).drawEnemy(delta, game.batch);
			}
			else
			{
				arrEnemy.remove(i);
				enemyKilled++;
			}
		}
		game.font.draw(game.batch,"Kills:" + enemyKilled, 10, 450);
		game.font.draw(game.batch,"XP:" + healthZABOR, 700, 450);
		//game.font.draw(game.batch,"enemy.state:" + arrEnemy.get(0).destrTime, 400, 450);
		game.font.draw(game.batch,"Record:" + game.record, 10, 430);

		for(int i = 0; i < arrAnimExplosive.size(); i++)
		{
			arrAnimExplosive.get(i).update(delta);
			if(arrAnimExplosive.get(i).frame == -1)
			{
				//arrAnimExplosive.remove(i);
				continue;
			}
			game.batch.draw(arrAnimExplosive.get(i).getFrame(), arrAnimExplosive.get(i).x, arrAnimExplosive.get(i).y);
			//arrAnimExplosive.get(i).update(delta);
		}


		game.batch.end();

		if(Gdx.input.isTouched())
		{
			if((!flag) && (time <= 0)){
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				spawnBullet(touchPos.x - 400, touchPos.y - 240);
				flag = true;
				time = cooldown;
				gun.update(touchPos.x - 400, touchPos.y - 240);
			}
		}
		else {
			flag = false;
		}

		for (Bullet bullet : arrBullet) {
			if(bullet.active) {
				bullet.rec.setOriginCenter();
				bullet.rec.setX((float) (bullet.rec.getX() + 200 * bullet.x * delta));
				bullet.rec.setY((float) (bullet.rec.getY() + 200 * bullet.y * delta));

				bullet.updateBullet();

				for (Enemy enemy : arrEnemy) {
					if ((enemy.state != 2) && (bullet.rectangle.overlaps(enemy.rectangle))) {
						enemy.health -= 1;

						bullet.active = false;
						//ExplosiveSound.play();
						arrAnimExplosive.add(new Animation(tr, 15, 1, false, bullet.rec.getX(), bullet.rec.getY()));
						break;
					}
				}
			}
		}

		for (Enemy enemy : arrEnemy) {
			enemy.updateEnemy(delta);
		}
		if(time > 0)
		{
			time -= delta;
		}
		if(timeEnemy > 0)
		{
			timeEnemy -= delta;
		}
		else
		{
			timeEnemy += timeEnemyDelay * (1 + (game.random.nextInt() % 25) / 100.0);
			spawnEnemy();
		}
		if(healthZABOR <= 0)
		{
			game.gameOver(enemyKilled);
			gc.GameOver();
		}
		if(win)
		{
			game.gameOver(enemyKilled);
			if(getLvl() >= 0)
			{
				game.lvlsProgress.set(getLvl(), Boolean.TRUE);
			}
			gc.GameOver();
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
	public void dispose () {

	}
}






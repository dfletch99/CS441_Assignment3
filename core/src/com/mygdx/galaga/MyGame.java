package com.mygdx.galaga;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game implements ApplicationListener {
	private Player player;
	private Shot shot;
	private Enemy[] enemies;
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture ship;
	private Texture enemy;
	private Texture bullet;
	private Texture gameOver;
	private Texture youWin;


	private int deadCounter;
	private double shotsFired = 0;
	private float w, h;
	private boolean shotFired = false;
	private boolean gameWon = false;
	private boolean gameLost = false;

	@Override
	public void create () {
		deadCounter = 0;
		enemies = new Enemy[50];
		float initial_y = (Gdx.graphics.getHeight()-150);
		for(int i = 0; i < 50; i++){
			enemies[i] = new Enemy();
            enemies[i].enemyHitbox.x = (150*(i%10));
            if(i > 39){
            	enemies[i].enemyHitbox.y = initial_y;
			}
            else if (i > 29){
            	enemies[i].enemyHitbox.y = initial_y - 100;
			}
            else if(i > 19){
            	enemies[i].enemyHitbox.y = initial_y - 200;
			}
            else if(i > 9){
            	enemies[i].enemyHitbox.y = initial_y - 300;
			}
            else enemies[i].enemyHitbox.y = initial_y - 400;
		}
        player = new Player();
		shot = new Shot();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0, 0, 0, 1);
		font.getData().setScale(5);
		ship = new Texture(Player.PLAYER_IMG_PATH);
		enemy = new Texture(Enemy.ENEMY_IMG_PATH);
		bullet = new Texture(Shot.SHOT_IMG_PATH);
		gameOver = new Texture("game_over_foreground.png");
		youWin = new Texture("you_win_foreground.png");
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(!gameWon && !gameLost) {
			batch.begin();
			batch.draw(ship, player.playerHitbox.x, player.playerHitbox.y, player.playerHitbox.width, player.playerHitbox.height);
			for (int i = 0; i < 50; i++) {
				if (!enemies[i].dead && enemies[i].enemyHitbox != null)
					batch.draw(enemy, enemies[i].enemyHitbox.x, enemies[i].enemyHitbox.y, enemies[i].enemyHitbox.width, enemies[i].enemyHitbox.height);
			}
			if (Gdx.input.justTouched() && !shotFired) {
				batch.draw(bullet, player.playerHitbox.x, player.playerHitbox.y);
				shot.shotHitbox.x = player.playerHitbox.x;
				shot.shotHitbox.y = player.playerHitbox.y;
				shotFired = true;
				shotsFired++;
			}
			if (shotFired) {
				shot.shotHitbox.y += shot.dy;
				batch.draw(bullet, shot.shotHitbox.x, shot.shotHitbox.y);
				for (int i = 0; i < 50; i++) {
					if (enemies[i].enemyHitbox != null && shot.shotHitbox.overlaps(enemies[i].enemyHitbox)) {
						shotFired = false;
						enemies[i].dead = true;
						enemies[i].enemyHitbox = null;
						deadCounter++;
						if(deadCounter >= 50) gameWon = true;
					}
					else if(shot.shotHitbox.y == h){
						shotFired = false;
					}
				}
			}
			batch.end();
			for (int i = 0; i < 50; i++) {
			    if(enemies[i].enemyHitbox != null) {
                    if(deadCounter > 45){
                        if(enemies[i].dx >= 0)
                            enemies[i].enemyHitbox.x += (enemies[i].dx + deadCounter/10.0) * 4;
                        else
                            enemies[i].enemyHitbox.x += (enemies[i].dx - deadCounter/10.0) * 4;
                    }
			        else if(deadCounter > 25){
                        if(enemies[i].dx >= 0)
                            enemies[i].enemyHitbox.x += (enemies[i].dx + deadCounter/10.0) * 1.75;
                        else
                            enemies[i].enemyHitbox.x += (enemies[i].dx - deadCounter/10.0) * 1.75;
                    }
			        else {
                        if (enemies[i].dx >= 0)
                            enemies[i].enemyHitbox.x += (enemies[i].dx + deadCounter / 10.0) * 1.5;
                        else
                            enemies[i].enemyHitbox.x += (enemies[i].dx - deadCounter / 10.0) * 1.5;
                    }
                    if (enemies[i].enemyHitbox.x > w || enemies[i].enemyHitbox.x < 0) {
                        for (int j = 0; j < 50; j++) {
                            if(enemies[j].enemyHitbox != null) {
                                enemies[j].dx *= -1;
                                enemies[j].enemyHitbox.y -= 10;
                                if(enemies[j].enemyHitbox.overlaps(player.playerHitbox)){
                                	gameLost = true;
								}
                            }
                        }
                    }
                }
			}
			player.playerHitbox.x += player.dx;
			if (player.playerHitbox.x > w || player.playerHitbox.x < 0)
				player.dx *= -1;
		}
		else if(gameWon){
			batch.begin();
			batch.draw(ship, player.playerHitbox.x, player.playerHitbox.y, player.playerHitbox.width, player.playerHitbox.height);
			batch.draw(gameOver, w - 1350, h/2 - 300, 1000, 1000);
			batch.draw(youWin, w - 1100, h - 750, 500, 500);
			font.draw(batch, "Accuracy: " + round(100 * (50.0 / shotsFired), 3) + "%", w - 1150, h - 850);
			batch.end();
		}
		else if(gameLost){
			batch.begin();
			for (int i = 0; i < 50; i++) {
				if (!enemies[i].dead && enemies[i].enemyHitbox != null)
					batch.draw(enemy, enemies[i].enemyHitbox.x, enemies[i].enemyHitbox.y, enemies[i].enemyHitbox.width, enemies[i].enemyHitbox.height);
			}
			batch.draw(ship, player.playerHitbox.x, player.playerHitbox.y, player.playerHitbox.width, player.playerHitbox.height);
			batch.draw(gameOver, w - 1350, h/2 - 300, 1000, 1000);
			font.draw(batch, "You Lost!", w-1000, h - 500);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		w = width - 175;
		h = height;
	}
	@Override
	public void dispose() {
		ship.dispose();
		enemy.dispose();
		batch.dispose();
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
